package com.example.mooncascade.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mooncascade.App
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.data.RetrofitCall
import com.example.mooncascade.db.ForecastDataBase
import com.example.mooncascade.di.component.AppComponent
import com.example.mooncascade.di.component.DaggerMainActivityComponent
import com.example.mooncascade.interfaces.OnItemsClickListener
import com.example.mooncascade.presenter.MainPresenter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemsClickListener{

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
            mainPresenter.loadDatafromInternet(forecast)
        }
        else if (!mainPresenter.isInternetExist() ){
            mainPresenter.loadDatafromCache()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }

    override fun onItemforecastClicked(forecastweather: ForecastWeather, position: Int) {
        if (position == 0){
            forecast_recyclerview.visibility = View.INVISIBLE
            cities_recyclerview.layoutManager = LinearLayoutManager(this)
            cities_recyclerview.adapter = CityRecyclerView(forecastweather,this)
            cities_recyclerview.visibility = View.VISIBLE
        }
    }

    override fun onCityClicked() {
        cities_recyclerview.visibility = View.INVISIBLE
        cardViewlay.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (forecast_recyclerview.parent != null){
            cities_recyclerview.visibility = View.INVISIBLE
            forecast_recyclerview.visibility = View.VISIBLE
        }
    }

}