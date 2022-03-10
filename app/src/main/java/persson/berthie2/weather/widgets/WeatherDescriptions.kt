package persson.berthie2.weather.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import persson.berthie2.weather.model.WeatherItem



/*
* Should be in Untils package i think? refactor T.T
* have not been able to check all descriptions match, Lower&Upper case sensitive from api matters
* */

@Composable
fun WeatherDescriptionFunc(weather: WeatherItem) {
    when (weather.weather[0].description) {
        "sky is clear" -> Text(text = "wow such sky")
        "clear sky" -> Text(text = "wow such sky")
        "few clouds" -> Text(text = "mm few clouds")
        "scattered clouds" -> Text(text = "le scattered clouds")
        "broken clouds" -> Text(text = "some clouds")
        "shower rain" -> Text(text = "wow such rain")
        "rain" -> Text(text = "le rain")
        "thunderstorm" -> Text(text = "much lightning")
        "snow" -> Text(text = "wow so white")
        "mist" -> Text(text = "much mist")
        "thunderstorm with light rain" -> Text(text = "thunderstorm with light rain")
        "thunderstorm with rain" -> Text(text = "le thunder and rain")
        "thunderstorm with heavy rain" -> Text(text = "le thunder and much rain")
        "light thunderstorm" -> Text(text = "light thunder")
        "heavy thunderstorm" -> Text(text = "much thunder")
        "ragged thunderstorm" -> Text(text = "ragged thunderstorm")
        "thunderstorm with light drizzle" -> Text(text = "thunderstorm with light drizzle")
        "thunderstorm with drizzle" -> Text(text = "thunderstorm with drizzle")
        "thunderstorm with heavy drizzle" -> Text(text = "thunderstorm with heavy drizzle")
        "light intensity drizzle" -> Text(text = "light intensity drizzle")
        "drizzle" -> Text(text = "wow drizzle")
        "heavy intensity drizzle" -> Text(text = "such drizzle")
        "light intensity drizzle rain" -> Text(text = "light intensity drizzle rain")
        "drizzle rain" -> Text(text = "drizzle rain")
        "heavy intensity drizzle rain" -> Text(text = "heavy intensity drizzle rain")
        "shower rain and drizzle" -> Text(text = "shower rain and drizzle")
        "heavy shower rain and drizzle" -> Text(text = "heavy shower rain and drizzle")
        "shower drizzle" -> Text(text = "shower drizzle")
        "light rain" -> Text(text = "such light rain")
        "moderate rain" -> Text(text = "moderate rain")
        "heavy intensity rain" -> Text(text = "heavy intensity rain")
        "very heavy rain" -> Text(text = "very heavy rain")
        "extreme rain" -> Text(text = "extreme rain")
        "freezing rain" -> Text(text = "freezing rain")
        "light intensity shower rain" -> Text(text = "light intensity shower rain")
        "heavy intensity shower rain" -> Text(text = "heavy intensity shower rain")
        "ragged shower rain" -> Text(text = "ragged shower rain")
        "light snow" -> Text(text = "such flakes")
        "heavy snow" -> Text(text = "wow much flakes")
        "sleet" -> Text(text = "Sleet")
        "light shower sleet" -> Text(text = "Light shower sleet")
        "shower sleet" -> Text(text = "Shower sleet")
        "light rain and snow" -> Text(text = "Light rain and snow")
        "rain and snow" -> Text(text = "Rain and snow")
        "light shower snow" -> Text(text = "Light shower snow")
        "shower snow" -> Text(text = "Shower snow")
        "heavy shower snow" -> Text(text = "Heavy shower snow")
        "overcast clouds" -> Text(text = "overcast clouds")

    }

}
