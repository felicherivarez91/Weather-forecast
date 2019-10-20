package com.example.mooncascade.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mooncascade.App
import com.example.mooncascade.R
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerMainActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.forecast_layout.*
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

        val forecast = retrofitCall.getforecastweather()

        forecast
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                         forecast_recyclerview.layoutManager = LinearLayoutManager(this)
                         forecast_recyclerview.adapter = ForecastRecyclerView(it)
                       }
    }

}

