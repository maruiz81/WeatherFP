package maruiz.com.weatherfp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(val speed: Double, val deg: Int)
