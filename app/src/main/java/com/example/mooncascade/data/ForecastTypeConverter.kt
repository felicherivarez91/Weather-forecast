package com.example.mooncascade.data

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*

/**
 * @author Dmitry Tkachuk
 * Created on 24.10.2019
 * All rights reserved
 */
class ForecastTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToForecastList(data: String?): List<ForecastList> {
        if (data == null || data == "") {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<ForecastList>>() {

        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun ForecastListToString(someObjects: List<ForecastList>): String {
        return gson.toJson(someObjects)
    }
}