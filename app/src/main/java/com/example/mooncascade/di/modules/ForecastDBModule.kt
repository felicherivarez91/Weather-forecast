package com.example.mooncascade.di.modules

import android.content.Context
import dagger.Provides
import javax.inject.Singleton
import androidx.room.Room
import com.example.mooncascade.db.ForecastDAO
import com.example.mooncascade.db.ForecastDataBase
import com.example.mooncascade.localizationsupport.LocaleManager
import dagger.Module

@Module
class ForecastDBModule{

    @Singleton
    @Provides
    fun provideForecastDatabase(context: Context): ForecastDataBase {
        return Room.databaseBuilder(
            context,
            ForecastDataBase::class.java, ForecastDataBase.DATABASENAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideShowDao(forecastDataBase: ForecastDataBase): ForecastDAO {
        return forecastDataBase.forecastdao()
    }

    @Singleton
    @Provides
    fun provideLocaleManager(): LocaleManager {
        return LocaleManager()
    }
}