package com.example.mooncascade.di.component

import com.example.mooncascade.App
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.db.ForecastDataBase
import com.example.mooncascade.di.modules.ContextModule
import com.example.mooncascade.di.modules.ForecastDBModule
import com.example.mooncascade.di.modules.RequestsModule
import com.example.mooncascade.presenter.MainPresenter
import com.example.mooncascade.localizationsupport.LocaleManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = (arrayOf(RequestsModule::class,ForecastDBModule::class,ContextModule::class)))
interface AppComponent {

    val retrofitCall : RetrofitCall

    val forecastdatabase : ForecastDataBase

    val mainpresenter : MainPresenter

    val localeManager : LocaleManager

    fun injectApp(myApplication: App)
}