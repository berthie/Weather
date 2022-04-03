package persson.berthie2.weather.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import persson.berthie2.weather.R
import persson.berthie2.weather.animations.rudolf
import persson.berthie2.weather.animations.simba
import persson.berthie2.weather.model.WeatherItem



/*
* Should be in Untils package i think? refactor T.T
* Rename Composables for easier understanding, icon sizes 50-100
* */

@Composable
fun WeatherIconFunc2(weather: WeatherItem) {

    when (weather.weather[0].icon) {
        "01d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.clearsky),
            contentDescription = "clear Sky"
        )
        "01n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.clearskynightt),
            contentDescription = "clear sky NIGHT"
        )
        "02d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.fewcloudsday),
            contentDescription = "few clouds"
        )
        "02n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.fewcloudsnight),
            contentDescription = "few clouds NIGHT"
        )
        "03d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.scatteredcloudsday),
            contentDescription = "scattered clouds"
        )
        "03n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.scatteredcloudsnight),
            contentDescription = "scattered clouds NIGHT"
        )
        "04d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.brokencloudsday),
            contentDescription = "broken clouds"
        )
        "04n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.broikencloudsnight),
            contentDescription = "broken clouds NIGHT"
        )
        "09d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.showerrainday),
            contentDescription = "shower rain"
        )
        "09n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.showerrainnight),
            contentDescription = "shower rain NIGHT"
        )
        "10d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.rainday),
            contentDescription = "rain"
        )
        "10n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.rainnight),
            contentDescription = "rain NIGHT"
        )
        "11d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.thunderstormday),
            contentDescription = "thunderstorm"
        )
        "11n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(
                id = R.drawable.thundernight
            ),
            contentDescription = "thunderstorm NIGHT"
        )
        "13d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.snowday),
            contentDescription = "snow"
        )
        "13n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.snownight),
            contentDescription = "snow NIGHT"
        )
        "50d" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.mistday),
            contentDescription = "mist"
        )
        "50n" -> Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.mistcopynight),
            contentDescription = "mist NIGHT"
        )
    }
}

@Composable
fun WeatherIconFunc(weather: WeatherItem) {

    when (weather.weather[0].icon) {
        "01d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.clearsky),
            contentDescription = "clear Sky"
        )
        "01n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.clearskynightt),
            contentDescription = "clear sky NIGHT"
        )
        "02d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.fewcloudsday),
            contentDescription = "few clouds"
        )
        "02n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.fewcloudsnight),
            contentDescription = "few clouds NIGHT"
        )
        "03d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.scatteredcloudsday),
            contentDescription = "scattered clouds"
        )
        "03n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.scatteredcloudsnight),
            contentDescription = "scattered clouds NIGHT"
        )
        "04d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.brokencloudsday),
            contentDescription = "broken clouds"
        )
        "04n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.broikencloudsnight),
            contentDescription = "broken clouds NIGHT"
        )
        "09d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.showerrainday),
            contentDescription = "shower rain"
        )
        "09n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.showerrainnight),
            contentDescription = "shower rain NIGHT"
        )
        "10d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.rainday),
            contentDescription = "rain"
        )
        "10n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.rainnight),
            contentDescription = "rain NIGHT"
        )
        "11d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.thunderstormday),
            contentDescription = "thunderstorm"
        )
        "11n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(
                id = R.drawable.thundernight
            ),
            contentDescription = "thunderstorm NIGHT"
        )
        "13d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.snowday),
            contentDescription = "snow"
        )
        "13n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.snownight),
            contentDescription = "snow NIGHT"
        )
        "50d" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.mistday),
            contentDescription = "mist"
        )
        "50n" -> Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.mistcopynight),
            contentDescription = "mist NIGHT"
        )
    }
}