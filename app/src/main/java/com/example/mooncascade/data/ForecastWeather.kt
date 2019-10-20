package com.example.mooncascade.data

import com.google.gson.annotations.SerializedName

data class ForecastWeather(@SerializedName("forecasts") val forecast: List<ForecastList>)

data class ForecastList(
                        @SerializedName("date") val mdate : String,
                        @SerializedName("day") val mday : Day,
                        @SerializedName("night") val mnight : Night
                       )

data class Day(
               @SerializedName("phenomenon") val mphenomenon : String,
               @SerializedName("tempmin") val mtempmin : String,
               @SerializedName("tempmax") val mtempmax : String,
               @SerializedName("text") val mtext : String,
               @SerializedName("peipsi") val mpeipsi : String
              )

data class Night(
                 @SerializedName("text") val mtext: String
                )