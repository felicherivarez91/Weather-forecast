package com.example.mooncascade.presenter

import android.content.Context
import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mooncascade.interfaces.BasePresenter
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.ui.ForecastRecyclerView
import com.example.mooncascade.ui.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter: BasePresenter<MainActivity> {

    private lateinit var mainView: MainActivity
    private var disposable : Disposable? = null
    @Volatile private var mweatherforecast : ForecastWeather? = null

    override fun onAttach(view: MainActivity) {
        this.mainView = view
    }

    override fun isInternetExist(): Boolean {
        val cm = mainView.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getActiveNetworkInfo()!= null
    }

    override fun loadDatafromInternet(forecast: Observable<ForecastWeather>) {
        disposable = forecast
                     .subscribeOn(Schedulers.newThread())
                     .observeOn(Schedulers.io())
                     .doOnTerminate { if (mweatherforecast != null)
                                 mainView.forecastDataBase.forecastdao().insert(mweatherforecast!!)
                      }
                     .doOnError { error ->
                                        System.err.println("The error message is: " + error.message)
                      }
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe{ mainView.forecast_recyclerview.layoutManager =
                                                                       LinearLayoutManager(mainView)
                          mainView.forecast_recyclerview.adapter = ForecastRecyclerView(it,mainView)
                          mweatherforecast = it
                      }
    }

    override fun loadDatafromCache() {
        Thread {
            mweatherforecast = mainView.forecastDataBase.forecastdao().getforecastWeather()
            mainView.runOnUiThread{
                        mainView.forecast_recyclerview.layoutManager = LinearLayoutManager(mainView)
                mainView.forecast_recyclerview.adapter =
                                                   ForecastRecyclerView(mweatherforecast!!,mainView)
            }
        }.start()
    }

    override fun onDetach() {
          disposable?.dispose()
    }

}