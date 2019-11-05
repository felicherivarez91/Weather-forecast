package com.example.mooncascade.interfaces

import com.example.mooncascade.data.ForecastWeather

/**
 * @author Dmitry Tkachuk
 * Created on 25.10.2019
 * All rights reserved
 */
interface OnItemsClickListener {

    fun onItemforecastClicked(forecastweather: ForecastWeather, position: Int)

}