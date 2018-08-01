package maruiz.com.weatherfp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clouds(val all: Int)
