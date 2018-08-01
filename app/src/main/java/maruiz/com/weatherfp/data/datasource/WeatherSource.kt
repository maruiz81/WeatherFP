package maruiz.com.weatherfp.data.datasource

import arrow.Kind
import arrow.core.Try
import arrow.core.right
import arrow.data.Reader
import arrow.data.ReaderApi
import arrow.data.map
import arrow.effects.IO
import arrow.effects.fix
import arrow.effects.monadDefer
import arrow.effects.typeclasses.Async
import arrow.typeclasses.bindingCatch
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import maruiz.com.weatherfp.data.model.CurrentWeatherModel
import maruiz.com.weatherfp.presentation.di.WeatherContext.GetWeatherContext

object WeatherSource {

    private fun <F, A, B> runInAsyncContext(f: () -> A, onError: (Throwable) -> B,
                                            onSuccess: (A) -> B, AC: Async<F>): Kind<F, B> =
            AC.async { callback ->
                async(CommonPool) {
                    val result = Try { f() }.fold(onError, onSuccess)
                    callback(result.right())
                }
            }

    fun fetchCurrentWeather(city: String): Reader<GetWeatherContext, IO<CurrentWeatherModel>> =
            ReaderApi.ask<GetWeatherContext>().map { ctx ->
                IO.monadDefer().bindingCatch {
                    val result = runInAsyncContext(f = {
                        ctx.weatherApiService.getCurrentWeather(city).execute().body()
                                ?: throw Exception()
                    },
                            onError = { IO.raiseError<CurrentWeatherModel>(it) },
                            onSuccess = { IO.just(it) }, AC = ctx.threading)
                            .bind()
                    result.bind()
                }.fix()
            }

}