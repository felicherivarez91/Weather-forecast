package com.example.mooncascade.interfaces

import com.example.mooncascade.data.ForecastWeather

interface OnItemsClickListener {

    fun onItemforecastClicked(forecastweather: ForecastWeather, position: Int)

}