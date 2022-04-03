package persson.berthie2.weather.animations

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.isActive
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

internal fun Modifier.rudolf(
    sizeRange: ClosedRange<Float> = 5f..12f,
    incrementFactorRange: ClosedRange<Float> = 0.4f..0.8f,
    angleSeed: Float = 25f,
    angleSeedRange: ClosedRange<Float> = -angleSeed..angleSeed,
    angleVariance: Float = .1f,
    angleDivider: Float = 10_000f,
    @FloatRange(from = 0.0, to = 1.0) density: Float = .1f
): Modifier = composed {
    var fluffyState by remember {
        mutableStateOf(
            SnowfallState(
                emptyList(),
                sizeRange,
                incrementFactorRange,
                angleSeed,
                angleSeedRange,
                angleVariance,
                angleDivider,
                density
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


private data class SnowfallState(
    val snowflakes: List<Snowflake>,
    val sizeRange: ClosedRange<Float> = 5f..12f,
    val incrementFactorRange: ClosedRange<Float> = 0.4f..0.8f,
    val angleSeed: Float = 25f,
    val angleSeedRange: ClosedRange<Float> = -angleSeed..angleSeed,
    val angleVariance: Float = .1f,
    val angleDivider: Float = 10_000f,
    @FloatRange(from = 0.0, to = 1.0) val density: Float = .1f
) {

    init {
        require(density in 0f..1f) { "Density must be between 0f and 1f, inclusive" }
    }

    fun resize(size: IntSize): SnowfallState = copy(
        snowflakes = createSnowFlakes(
            size,
            sizeRange,
            incrementFactorRange,
            angleSeed,
            angleSeedRange,
            angleVariance,
            angleDivider,
            density
        )
    )

    companion object {

        private fun createSnowFlakes(
            canvasSize: IntSize,
            sizeRange: ClosedRange<Float>,
            incrementFactorRange: ClosedRange<Float>,
            angleSeed: Float,
            angleSeedRange: ClosedRange<Float>,
            angleVariance: Float,
            angleDivider: Float,
            density: Float
        ): List<Snowflake> {
            val snowflakeCount = (canvasSize.area * density / DENSITY_DIVIDER).roundToInt()
            return List(snowflakeCount) {
                Snowflake(
                    radius = sizeRange.random(),
                    position = canvasSize.randomPosition(),
                    incrementFactor = incrementFactorRange.random(),
                    angleSeedRange = angleSeedRange,
                    angle = angleSeed.random() / angleSeed * angleVariance + (Math.PI.toFloat() / 2f) - (angleVariance / 2f),
                    angleDivider = angleDivider,
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

private class Snowflake(
    private val radius: Float,
    private val canvasSize: IntSize,
    private val incrementFactor: Float,
    private val angleSeedRange: ClosedRange<Float>,
    private val angleDivider: Float,
    angle: Float,
    position: Offset,
) {
    val paint: Paint = Paint().apply {
        isAntiAlias = true
        color = Color.White
        style = PaintingStyle.Fill
        alpha = incrementFactor
    }
    var position by mutableStateOf(position)
    var angle by mutableStateOf(angle)

    fun draw(canvas: Canvas) {
        val currentOffsetX = position.x
        val currentOffsetY = position.y
        //canvas.drawCircle(center = position, radius = radius, paint = paint)
        val lineStart = Offset(currentOffsetX,currentOffsetY)
       val linEnd = Offset(currentOffsetX , currentOffsetY + 12f)
        canvas.drawLine(p1 = lineStart, p2 = linEnd, paint = paint)
    }

    fun update(elapsedMillis: Long) {
        val increment = incrementFactor * (elapsedMillis / frameDurationAt60Fps) * baseSpeedAt60Fps
        val deltaY = increment * sin(angle)
        val deltaX = increment * cos(angle)
        position = Offset(position.x + deltaX, position.y + deltaY)
        angle += angleSeedRange.random() / angleDivider
        if (position.y - radius > canvasSize.height) {
            position = position.copy(y = -radius)
        }
        if (position.x < -radius || position.x > canvasSize.width + radius) {
            position = position.copy(x = canvasSize.width.random().toFloat(), y = -radius)
        }
    }

    companion object {
        private const val frameDurationAt60Fps = 16
        private const val baseSpeedAt60Fps = 2f
    }
}