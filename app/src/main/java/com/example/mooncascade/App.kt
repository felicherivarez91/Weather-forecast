package com.example.mooncascade

import android.app.Activity
import android.app.Application
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerAppComponent

class App : Application(){

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().build()
        component.injectApp(this)
    }

    operator fun get(activity: Activity): App {
        return activity.application as App
    }

    fun getApplicationComponent(): AppComponent {
        return component
    }
    companion object {
        lateinit var instance: App private set
    }
}
