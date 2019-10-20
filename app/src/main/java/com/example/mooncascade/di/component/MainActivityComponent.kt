package com.example.mooncascade.di.component

import com.example.mooncascade.ui.MainActivity
import com.example.mooncascade.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface MainActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}