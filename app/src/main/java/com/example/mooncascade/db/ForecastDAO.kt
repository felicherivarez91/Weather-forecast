package com.example.mooncascade.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mooncascade.data.ForecastWeather

@Dao
interface ForecastDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastWeather: ForecastWeather)

    @Query("SELECT * FROM forecast")
    fun getforecastWeather() : ForecastWeather
}