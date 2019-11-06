package com.example.mooncascade.data

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Dmitry Tkachuk
 * Created on 17.10.2019
 * All rights reserved
 */
interface RetrofitCall {

    @GET("/api/estonia/forecast")
    fun getforecastweather() : Observable<ForecastWeather>

}