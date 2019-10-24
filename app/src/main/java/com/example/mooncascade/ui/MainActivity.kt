package com.example.mooncascade.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mooncascade.App
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.db.ForecastDataBase
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerMainActivityComponent
import com.example.mooncascade.presenter.MainPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appcomponent: AppComponent

    @Inject
    lateinit var retrofitCall: RetrofitCall

    @Inject
    lateinit var mainPresenter: MainPresenter

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
        mainPresenter.onAttach(this)

        if (mainPresenter.isInternetExist()){
            forecast = retrofitCall.getforecastweather()
            mainPresenter.setData(forecast)
        }
        else{
            Thread {
                forecast_recyclerview.layoutManager = LinearLayoutManager(this)
                forecast_recyclerview.adapter =
                           ForecastRecyclerView(forecastDataBase.forecastdao().getforecastWeather())
            }.start()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }

}

