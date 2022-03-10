package persson.berthie2.weather.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import persson.berthie2.weather.R
import persson.berthie2.weather.model.WeatherItem
import persson.berthie2.weather.ui.theme.bgColorCenter
import persson.berthie2.weather.ui.theme.darkerOrange
import persson.berthie2.weather.ui.theme.lightOrange
import persson.berthie2.weather.ui.theme.mediumRed
import persson.berthie2.weather.utils.formatDate
import persson.berthie2.weather.utils.formatDateTime
import persson.berthie2.weather.utils.formatDecimals


@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Card(
        modifier = Modifier
            .fillMaxWidth()
           .padding(1.dp),
        shape = CircleShape.copy(
            topStart = CornerSize(6.dp),
            topEnd = CornerSize(6.dp),
            bottomEnd = CornerSize(6.dp),
            bottomStart = CornerSize(6.dp)
        ),
        backgroundColor = bgColorCenter,
        border = BorderStroke(2.dp, darkerOrange),

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                formatDate(weather.dt).split(",")[0],
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(start = 5.dp)

            )
            WeatherIconFunc(weather = weather)
            WeatherDescriptionFunc(weather = weather)
            //WeatherStateImage(imageUrl = imageUrl)
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = mediumRed, fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "°")
                }
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "${weather.humidity}%",
                style = MaterialTheme.typography.caption
            )
        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "${weather.pressure} psi",
                style = MaterialTheme.typography.caption
            )
        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "${weather.speed} ms",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun SunriseAndSunset(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 12.dp, end = 5.dp)
            )
            Text(
                text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 5.dp)
            )
            Text(
                text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
    }

}