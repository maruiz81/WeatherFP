package maruiz.com.weatherfp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherModel(val coord: Coord, val weather: List<Weather>, val base: String,
                               val main: Main, val visibility: Int, val wind: Wind, val clouds: Clouds,
                               val dt: Int, val sys: Sys, val id: Int, val name: String, val cod: Int)
