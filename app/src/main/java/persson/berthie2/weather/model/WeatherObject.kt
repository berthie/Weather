package persson.berthie2.weather.model


import com.google.gson.annotations.SerializedName

data class WeatherObject(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)