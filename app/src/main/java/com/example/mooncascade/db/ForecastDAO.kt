package com.example.mooncascade.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mooncascade.data.ForecastWeather

/*
 * @author Dmitry Tkachuk
 * Created on 23.10.2019
 */
@Dao
interface ForecastDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastWeather: ForecastWeather)

    @Query("SELECT * FROM forecast")
    fun getforecastWeather() : ForecastWeather

}