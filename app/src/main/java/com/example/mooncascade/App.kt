package com.example.mooncascade

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerAppComponent
import com.example.mooncascade.di.modules.ContextModule
import com.example.mooncascade.localizationsupport.LocaleManager
import com.example.mooncascade.localizationsupport.LocaleManager.Companion.DEFAULT_LANGUAGE
import javax.inject.Inject

/**
 * @author Dmitry Tkachuk
 * Created on 20.10.2019
 * All rights reserved
 */
class App : Application(){

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
        component.injectApp(this)
    }

    operator fun get(activity: Activity): App {
        return activity.application as App
    }

    fun getApplicationComponent(): AppComponent {
        return component
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager().setLocale(base, DEFAULT_LANGUAGE))
    }

    companion object {
        lateinit var instance: App private set
    }
}
