package com.example.mooncascade.data

import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitCall {

    @GET("/api/estonia/forecast")
    fun getforecastweather() : Observable<ForecastWeather>

}