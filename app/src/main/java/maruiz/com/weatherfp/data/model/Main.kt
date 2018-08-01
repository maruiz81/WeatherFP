package maruiz.com.weatherfp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(val temp: Float, val pressure: Int, val humidity: Int,
                @Json(name = "temp_min") val tempMin: Float,
                @Json(name = "temp_max") val tempMax: Float)
