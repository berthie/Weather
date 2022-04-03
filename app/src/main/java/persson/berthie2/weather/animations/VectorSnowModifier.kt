package persson.berthie2.weather.animations

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.isActive
import persson.berthie2.weather.R
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

internal fun Modifier.simba(
    heightRange: ClosedRange<Float> = 8f..20f,
    incrementFactorRange: ClosedRange<Float> = 0.4f..0.8f,
    angleSeed: Float = 25f,
    fallAngleSeedRange: ClosedRange<Float> = -angleSeed..angleSeed,
    fallAngleVariance: Float = .1f,
    fallAngleDivider: Float = 10_000f,
    rotationAngleRadSeed: Float = 45f,
    rotationSpeedRadPerTick: ClosedRange<Float> = -0.05f..0.05f,
    @FloatRange(from = 0.0, to = 1.0) snowDensity: Float = .1f
): Modifier = composed {
    val context = LocalContext.current
    var fluffyState by remember {
        mutableStateOf(
            VectorSnowfallState(
                snowflakes = emptyList(),
                heightRange = heightRange,
                incrementFactorRange = incrementFactorRange,
                fallAngleSeed = angleSeed,
                fallAngleSeedRange = fallAngleSeedRange,
                fallAngleVariance = fallAngleVariance,
                fallAngleDivider = fallAngleDivider,
                rotationAngleRadSeed = rotationAngleRadSeed,
                rotationSpeedRadPerTick = rotationSpeedRadPerTick,
                snowDensity = snowDensity,
                context = context,
            )
        )
    }
    var lastTick by remember { mutableStateOf(-1L) }

    LaunchedEffect(Unit) {
        while (isActive) {
            withFrameMillis { newTick ->
                val eleapsedMillis = newTick - lastTick
                val wasFirstFrame = lastTick < 0
                lastTick = newTick
                if (wasFirstFrame) return@withFrameMillis

                for (snowflake in fluffyState.snowflakes) {
                    snowflake.update(eleapsedMillis)
                }
            }
        }
    }

    onSizeChanged { size ->
        fluffyState = fluffyState.resize(size)
    }
        .clipToBounds()
        .drawWithContent {
            drawContent()
            val canvas = drawContext.canvas
            for (snowflake in fluffyState.snowflakes) {
                snowflake.draw(canvas)
            }
        }
}


private data class VectorSnowfallState(
    val snowflakes: List<VectorSnowflake>,
    val heightRange: ClosedRange<Float>,
    val incrementFactorRange: ClosedRange<Float>,
    val fallAngleSeed: Float ,
    val fallAngleSeedRange: ClosedRange<Float>,
    val fallAngleVariance: Float ,
    val fallAngleDivider: Float,
    val rotationAngleRadSeed: Float,
    val rotationSpeedRadPerTick: ClosedRange<Float>,
    @FloatRange(from = 0.0, to = 1.0) val snowDensity: Float,
    val context: Context
) {

    init {
        require(snowDensity in 0f..1f) { "Density must be between 0f and 1f, inclusive" }
    }

    fun resize(size: IntSize): VectorSnowfallState = copy(
        snowflakes = createVectorSnowFlakes(
            canvasSize = size,
            displayDensity = context.resources.displayMetrics.density,
            heightRange = heightRange,
            incrementFactorRange = incrementFactorRange,
            fallAngleRadSeed = fallAngleSeed,
            fallAngleRadSeedRange = fallAngleSeedRange,
            fallAngleVariance = fallAngleVariance,
            fallAngleDivider = fallAngleDivider,
            rotationAngleRadSeed = rotationAngleRadSeed,
            rotationSpeedRadPerTick = rotationSpeedRadPerTick,
            snowDensity = snowDensity
        ) {
            checkNotNull(AppCompatResources.getDrawable(context, it)) {"Snowflake drawable not found"}
                .apply { setBounds(0,0, intrinsicWidth, intrinsicHeight) }
        }
    )

    companion object {

        private fun createVectorSnowFlakes(
            canvasSize: IntSize,
            displayDensity: Float,
            heightRange: ClosedRange<Float>,
            incrementFactorRange: ClosedRange<Float>,
            fallAngleRadSeed: Float,
            fallAngleRadSeedRange: ClosedRange<Float>,
            fallAngleVariance: Float,
            fallAngleDivider: Float,
            rotationAngleRadSeed: Float,
            rotationSpeedRadPerTick: ClosedRange<Float>,
            snowDensity: Float,
            snowflakeDrawableProvider: (id: Int) -> Drawable
        ): List<VectorSnowflake> {
            val snowflakeCount = (canvasSize.area * snowDensity / DENSITY_DIVIDER).roundToInt()
            return List(snowflakeCount) {
                VectorSnowflake(
                    drawable = snowflakeDrawableProvider(SnowFlakeDrawable.pick().id),
                    height = heightRange.random() * displayDensity,
                    position = canvasSize.randomPosition(),
                    incrementFactor = incrementFactorRange.random(),
                    fallAngleRadSeedRange = fallAngleRadSeedRange,
                    fallAngleRad = fallAngleRadSeed.random() / fallAngleRadSeed * fallAngleVariance + (Math.PI.toFloat() / 2f) - (fallAngleVariance / 2f),
                    fallAngleDivider = fallAngleDivider,
                    rotationAngleRad = rotationAngleRadSeed.random(),
                    rotationSpeedRadPerTick = rotationSpeedRadPerTick.random(),
                    canvasSize = canvasSize
                )
            }
        }

        private val IntSize.area: Int
            get() = width * height

        private const val DENSITY_DIVIDER = 500f

    }
}

private fun IntSize.randomPosition() =
    Offset(width.random().toFloat(), height.random().toFloat())

private fun Int.random(): Int = ThreadLocalRandom.current().nextInt(this)
private fun ClosedRange<Float>.random() =
    ThreadLocalRandom.current().nextFloat() * (endInclusive - start) + start

private fun Float.random(): Float = ThreadLocalRandom.current().nextFloat() * this

@Suppress("unused")
private enum class SnowFlakeDrawable(@DrawableRes val id: Int) {
    BERT(R.drawable.ic_snow),
    OLAF(R.drawable.ic_vectorsnow);

    companion object {
        fun pick() = values().random()
    }

}

private class VectorSnowflake(
    private val drawable: Drawable,
    private val height: Float,
    private val canvasSize: IntSize,
    private val incrementFactor: Float,
    private val fallAngleRadSeedRange: ClosedRange<Float>,
    private val fallAngleDivider: Float,
    private val rotationSpeedRadPerTick: Float,
    fallAngleRad: Float,
    rotationAngleRad: Float,
    position: Offset,
) {

    var position by mutableStateOf(position)
    var fallAngleRad by mutableStateOf(fallAngleRad)
    var rotationAngleRad by mutableStateOf(rotationAngleRad)

    fun draw(canvas: Canvas) {

        canvas.withSave {
            val scaleFactor = height / drawable.intrinsicHeight
            val halfWidth = drawable.intrinsicWidth / 2f
            val halfHeight = drawable.intrinsicHeight / 2f
            canvas.translate(position.x , position.y )
            canvas.scale(scaleFactor)
            canvas.rotateRad(rotationAngleRad, halfWidth, halfHeight)
            drawable.draw(canvas.nativeCanvas)
        }
    }

    fun update(elapsedMillis: Long) {
        val increment = incrementFactor * (elapsedMillis / frameDurationAt60Fps) * baseSpeedAt60Fps
        val deltaY = increment * sin(fallAngleRad)
        val deltaX = increment * cos(fallAngleRad)
        position = Offset(position.x + deltaX, position.y + deltaY)
        fallAngleRad += fallAngleRadSeedRange.random() / fallAngleDivider
        rotationAngleRad += rotationSpeedRadPerTick
        if (rotationAngleRad > 2 * Math.PI) rotationAngleRad = 0f
        if (position.y - height > canvasSize.height) {
            position = position.copy(y = -height)
        }
        if (position.x < -height || position.x > canvasSize.width + height) {
            position = position.copy(x = canvasSize.width.random().toFloat(), y = -height)
        }
    }

    companion object {
        private const val frameDurationAt60Fps = 16
        private const val baseSpeedAt60Fps = 2f
    }
}