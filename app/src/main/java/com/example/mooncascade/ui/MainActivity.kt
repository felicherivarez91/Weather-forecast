package com.example.mooncascade.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mooncascade.App
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.db.ForecastDataBase
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerMainActivityComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appcomponent: AppComponent

    @Inject
    lateinit var retrofitCall: RetrofitCall

    @Inject
    lateinit var forecastDataBase: ForecastDataBase

    private lateinit var forecast : Observable<ForecastWeather>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appcomponent = App.instance[this].getApplicationComponent()
        val activityComponent = DaggerMainActivityComponent.builder().
             appComponent(appcomponent)
            .build()
        activityComponent.injectMainActivity(this)

        forecast = retrofitCall.getforecastweather()

        forecast.
            subscribeOn(Schedulers.io()).
            subscribe{
                Log.d("app","suka epta" + forecastDataBase.forecastdao().getforecastWeather())
            }

        forecast
                .subscribeOn(Schedulers.newThread()).
                observeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                forecast_recyclerview.layoutManager = LinearLayoutManager(this)
                forecast_recyclerview.adapter = ForecastRecyclerView(it)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        forecast.
            subscribeOn(Schedulers.io()).
            subscribe{
                      forecastDataBase.forecastdao().insert(it)
                     }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getActiveNetworkInfo()!= null
    }

}

