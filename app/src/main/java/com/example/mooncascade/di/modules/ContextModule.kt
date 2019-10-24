package com.example.mooncascade.di.modules

import android.content.Context
import com.example.mooncascade.presenter.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun providePresenter(): MainPresenter {
        return MainPresenter()
    }
}