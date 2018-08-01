package maruiz.com.weatherfp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(val type: Int, val id: Int, val message: Double, val country: String,
               val sunrise: Int, val sunset: Int)

