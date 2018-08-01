package maruiz.com.weatherfp.domanain.usecase

import arrow.data.Reader
import arrow.effects.IO
import maruiz.com.weatherfp.data.datasource.WeatherSource
import maruiz.com.weatherfp.data.model.CurrentWeatherModel
import maruiz.com.weatherfp.presentation.di.WeatherContext

fun getWeatherUseCase(city: String): Reader<WeatherContext.GetWeatherContext, IO<CurrentWeatherModel>> =
        WeatherSource.fetchCurrentWeather(city)