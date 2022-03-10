package persson.berthie2.weather.screens.mainscreen

import android.util.Log
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import persson.berthie2.weather.data.DataOrException
import persson.berthie2.weather.model.Weather
import persson.berthie2.weather.model.WeatherItem
import persson.berthie2.weather.navigation.WeatherScreens
import persson.berthie2.weather.ui.theme.*

import persson.berthie2.weather.utils.formatDate

import persson.berthie2.weather.utils.formatDecimals
import persson.berthie2.weather.widgets.*

@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?,

    ) {


    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = city.toString())
    }.value

    if (weatherData.loading == true) {

    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController = navController)
    }

}

@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController
) {


    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name + ",${weather.city.country}",

                navController = navController,
                onAddAction = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                },
                elevation = 5.dp
            ) {
                Log.d("NAVBUTTON", "MainScaffold: Clicked")
            }
        }
    ) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {
    val weather = data.list[0]
    /*
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    * */

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(listOf(bgColorCenter, bgColorEdge))
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)

            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatDate(weather.dt),
                style = MaterialTheme.typography.caption,
                color = mediumOrange,
                modifier = Modifier.padding(6.dp)
            )
            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .size(200.dp)
                ,
                shape = CircleShape,
                color = lightOrange

            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Image
                    WeatherIconFunc2(weather = weather)

                    Text(
                        text = formatDecimals(weather.temp.day) + "°",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = weather.weather[0].main,
                        style = MaterialTheme.typography.body2,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            HumidityWindPressureRow(weather = weather)
            Divider(
                color = darkerOrange,
                thickness = 2.dp,
                startIndent = 10.dp,

            )
            SunriseAndSunset(weather = weather)
            Text(
                text = "This Week",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    ,
                shape = RoundedCornerShape(size = 14.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.
                        background(bgColorCenter)
                        .padding(2.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {
                    items(items = data.list) { item: WeatherItem ->
                        WeatherDetailRow(weather = item)
                    }
                }
            }
        }
    }


}

/*ImageLoader with Coil
*
* @Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "icon Image",
        modifier = Modifier.size(80.dp)
    )
}
*
* */


