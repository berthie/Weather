package persson.berthie2.weather.repository

import android.util.Log
import persson.berthie2.weather.data.DataOrException
import persson.berthie2.weather.model.Weather
import persson.berthie2.weather.model.WeatherObject
import persson.berthie2.weather.network.WeatherApi
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(
        cityQuery: String,
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            Log.d("REX", "getweather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getweather: $response")
        return DataOrException(data = response)
    }
}