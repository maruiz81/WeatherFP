package maruiz.com.weatherfp.data.service

import maruiz.com.weatherfp.data.model.CurrentWeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("weather?units=metric&apikey=$API_TOKEN")
    fun getCurrentWeather(@Query("q") city: String): Call<CurrentWeatherModel>
}