package com.example.mooncascade.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mooncascade.App
import com.example.mooncascade.R
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerMainActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appcomponent: AppComponent

    @Inject
    lateinit var retrofitCall: RetrofitCall

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appcomponent = App.instance[this].getApplicationComponent()
        val activityComponent = DaggerMainActivityComponent.builder().appComponent(appcomponent)
            .build()
        activityComponent.injectMainActivity(this)

        var forecast = retrofitCall.getforecastweather()
        forecast
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { this.emitItems(it) }
    }


private fun emitItems(it: Any) {

    }


}

