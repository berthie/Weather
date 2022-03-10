package persson.berthie2.weather.screens.mainscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import persson.berthie2.weather.data.DataOrException
import persson.berthie2.weather.model.Weather
import persson.berthie2.weather.model.WeatherObject
import persson.berthie2.weather.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getWeatherData(
        city: String
    ): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }

}
