package maruiz.com.weatherfp.presentation.di

import arrow.effects.IO
import arrow.effects.async
import maruiz.com.weatherfp.data.service.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

sealed class WeatherContext {
    private val baseUrl = "http://api.openweathermap.org/data/2.5/"

    private val client: OkHttpClient

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    val retrofit: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

    val threading = IO.async()

    class GetWeatherContext : WeatherContext() {
        val weatherApiService: WeatherService = retrofit.create(WeatherService::class.java)
    }
}