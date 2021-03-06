package com.example.mooncascade.db

import androidx.room.*
import com.example.mooncascade.data.ForecastTypeConverter
import com.example.mooncascade.data.ForecastWeather

/**
 * @author Dmitry Tkachuk
 * Created on 23.10.2019
 * All rights reserved
 */
@Database(entities = [ForecastWeather::class],version = 1, exportSchema = false)
@TypeConverters(ForecastTypeConverter::class)
abstract class  ForecastDataBase : RoomDatabase() {

    abstract fun forecastdao() : ForecastDAO

    companion object{
        val DATABASENAME : String = "weatherforecastdatabase.db"
    }
}