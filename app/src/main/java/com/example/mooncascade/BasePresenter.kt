package com.example.mooncascade

import android.app.Activity
import com.example.mooncascade.data.ForecastWeather
import io.reactivex.Observable

interface BasePresenter<in T: Activity> {

    fun onAttach(view: T)
    fun isInternetExist() : Boolean
    fun loadDatafromInternet(forecast : Observable<ForecastWeather>)
    fun loadDatafromCache()
    fun onDetach()

}