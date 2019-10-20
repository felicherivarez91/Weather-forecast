package com.example.mooncascade.data

import com.google.gson.annotations.SerializedName

data class ForecastWeather(@SerializedName("forecasts") val forecast: List<ForecastList>)

data class ForecastList(@SerializedName("date") val mdate : String)