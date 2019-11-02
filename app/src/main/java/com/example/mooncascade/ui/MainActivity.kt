package com.example.mooncascade.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import android.view.Menu
import android.view.MenuItem
import com.example.mooncascade.R.drawable
import com.example.mooncascade.localizationsupport.LocaleManager
import com.example.mooncascade.localizationsupport.LocaleManager.Companion.DEFAULT_LANGUAGE
import com.example.mooncascade.localizationsupport.LocaleManager.Companion.ESTONIAN_LANGUAGE
import com.example.mooncascade.localizationsupport.LocaleManager.Companion.RUSSIAN_LANGUAGE
import com.example.mooncascade.localizationsupport.LocaleManager.Companion.UKRAINIAN_LANGUAGE
import android.view.KeyEvent

class MainActivity : AppCompatActivity(), OnItemsClickListener{

    @Inject
    lateinit var retrofitCall: RetrofitCall

    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var forecastDataBase: ForecastDataBase

    @Inject
    lateinit var locale : LocaleManager

    private lateinit var forecast : Observable<ForecastWeather>
    private lateinit var appcomponent: AppComponent

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

    override fun onStart() {
        super.onStart()
        when (locale.getLanguage(this)) {
            UKRAINIAN_LANGUAGE -> {
                locale.setLocale(this, UKRAINIAN_LANGUAGE)
                setTitle(R.string.app_name)
            }
            RUSSIAN_LANGUAGE -> {
                locale.setLocale(this, RUSSIAN_LANGUAGE)
                setTitle(R.string.app_name)
            }
            ESTONIAN_LANGUAGE -> {
                locale.setLocale(this, ESTONIAN_LANGUAGE)
                setTitle(R.string.app_name)
            }
            DEFAULT_LANGUAGE -> {
                locale.setLocale(this, DEFAULT_LANGUAGE)
                setTitle(R.string.app_name)
            }
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            cities_recyclerview.visibility = View.INVISIBLE
            forecast_recyclerview.visibility = View.VISIBLE
            true
        }
        else super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?) :Boolean {
        when (locale.getLanguage(this)) {
            UKRAINIAN_LANGUAGE -> menu!!.getItem(0).setIcon(drawable.ua)
            RUSSIAN_LANGUAGE -> menu!!.getItem(0).setIcon(drawable.ru)
            ESTONIAN_LANGUAGE -> menu!!.getItem(0).setIcon(drawable.est)
            DEFAULT_LANGUAGE -> menu!!.getItem(0).setIcon(drawable.uk)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (locale.getLanguage(this)) {
            DEFAULT_LANGUAGE -> {
                item.setIcon(drawable.ua)
                locale.setLocale(this, UKRAINIAN_LANGUAGE)
            }
            UKRAINIAN_LANGUAGE -> {
                item.setIcon(drawable.ru)
                locale.setLocale(this, RUSSIAN_LANGUAGE)
            }
            RUSSIAN_LANGUAGE -> {
                item.setIcon(drawable.est)
                locale.setLocale(this, ESTONIAN_LANGUAGE)
            }
            ESTONIAN_LANGUAGE -> {
                item.setIcon(drawable.uk)
                locale.setLocale(this, DEFAULT_LANGUAGE)
            }
        }
        recreate()
        return true
    }

}