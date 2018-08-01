package maruiz.com.weatherfp.presentation.presenter

import android.util.Log
import arrow.data.ReaderApi
import arrow.data.flatMap
import arrow.data.map
import maruiz.com.weatherfp.domanain.usecase.getWeatherUseCase
import maruiz.com.weatherfp.presentation.di.WeatherContext.GetWeatherContext

object MainActivityPresenter {

    private const val TAG = "MainActivityPresenter"

    fun getWeather(city: String) = ReaderApi.ask<GetWeatherContext>()
            .flatMap {
                getWeatherUseCase(city).map {
                    it.unsafeRunAsync {
                        it.fold({ error -> Log.e(TAG, "Error", error) },
                                { success -> Log.d(TAG, "Success!!! ${success.name}") })
                    }
                }
            }
}