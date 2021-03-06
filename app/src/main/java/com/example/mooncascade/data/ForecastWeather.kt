package com.example.mooncascade.data

import androidx.room.*
import com.google.gson.annotations.SerializedName

/**
 * @author Dmitry Tkachuk
 * Created on 23.10.2019
 * All rights reserved
 */

const val WEATHER_LOCATION_ID = 0

@Entity(tableName = "forecast")
data class ForecastWeather(
                           @SerializedName("forecasts") val forecast: List<ForecastList>
                          ){
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_LOCATION_ID
}

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
               @SerializedName("peipsi") val mpeipsi : String?,
               @SerializedName("sea") val msea: String?,
               @SerializedName("places") val mplaces : List<Places>
              )

data class Night(
                 @SerializedName("phenomenon") val mphenomenon : String,
                 @SerializedName("tempmin") val mtempmin: String,
                 @SerializedName("tempmax") val mtempmax: String,
                 @SerializedName("text") val mtext: String,
                 @SerializedName("peipsi") val mpeipsi : String?,
                 @SerializedName("sea") val msea: String?,
                 @SerializedName("places") val mplaces : List<Places>
                )

data class Places(
                  @SerializedName("name") val mplacesname : String,
                  @SerializedName("tempmin") val mtempmin : String?,
                  @SerializedName("tempmax") val mtempmax : String?,
                  @SerializedName("phenomenon") val mphenomenon : String
                 )


