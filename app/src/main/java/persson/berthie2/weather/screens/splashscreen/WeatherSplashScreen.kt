package persson.berthie2.weather.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import persson.berthie2.weather.R
import persson.berthie2.weather.navigation.WeatherScreens
import persson.berthie2.weather.ui.theme.darkerOrange
import persson.berthie2.weather.ui.theme.lightOrange


@Composable
fun WeatherSplashScreen(navController: NavController) {
    val defaultCity = "Helsingborg"
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                0.9f,
                tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(8f)
                            .getInterpolation(it)
                    }
                )
            )
            delay(2000L)
            navController.navigate(WeatherScreens.MainScreen.name + "/$defaultCity")
        }
    )
    Surface(
        modifier = Modifier
            .clip(CircleShape)
            .size(330.dp)
            .background(Brush.radialGradient(listOf(lightOrange, darkerOrange)), alpha = 0.8f)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.Transparent,
        border = BorderStroke(width = 3.dp, color = darkerOrange),


        ) {
        Column(
            modifier = Modifier
                .padding(1.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.thunderstormday),
                contentDescription = "Sun",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(95.dp)
            )
            Text(
                text = stringResource(R.string.DogeWeather),
                color = Color.White,
                style = MaterialTheme.typography.body1,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

