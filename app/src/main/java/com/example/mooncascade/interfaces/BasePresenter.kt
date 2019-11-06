package com.example.mooncascade.interfaces

import android.app.Activity
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.databinding.ActivityMainBinding
import io.reactivex.Observable

/**
 * @author Dmitry Tkachuk
 * Created on 18.10.2019
 * All rights reserved
 */
interface BasePresenter<in T: Activity> {

    fun onAttach(view: T)
    fun isInternetExist() : Boolean
    fun loadDatafromInternet(forecast : Observable<ForecastWeather>)
    fun loadDatafromCache()
    fun onDetach()
    fun  setbinding(binding : ActivityMainBinding)

}