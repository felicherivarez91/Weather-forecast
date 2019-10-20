package com.example.mooncascade.di.component

import com.example.mooncascade.App
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.di.modules.RequestsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = (arrayOf(RequestsModule::class)))
interface AppComponent {

    val retrofitCall : RetrofitCall

    fun injectApp(myApplication: App)
}