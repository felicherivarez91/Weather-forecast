package com.example.mooncascade.presenter

import android.content.Context
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.interfaces.BasePresenter
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.ui.ForecastRecyclerView
import com.example.mooncascade.ui.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DividerItemDecoration



class MainPresenter: BasePresenter<MainActivity> {

    private lateinit var mainView: MainActivity
    private var disposable : Disposable? = null
    @Volatile private var mweatherforecast : ForecastWeather? = null

    override fun onAttach(view: MainActivity) {
        this.mainView = view
    }

    override fun isInternetExist(): Boolean {
        val cm = mainView.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    //Load and save data from internet
    override fun loadDatafromInternet(forecast: Observable<ForecastWeather>) {
        disposable = forecast
                     .subscribeOn(Schedulers.newThread())
                     .observeOn(Schedulers.io())
                      //save the data when the operation has ended
                     .doOnTerminate { if (mweatherforecast != null)
                                 mainView.forecastDataBase.forecastdao().insert(mweatherforecast!!)
                      }
                     .doOnError { error ->
                                        System.err.println("The error message is: " + error.message)
                      }
                     .observeOn(AndroidSchedulers.mainThread())
                     //Subscribe and send the data to RecyclerView
                     .subscribe({ mainView.forecast_recyclerview.layoutManager = LinearLayoutManager(mainView)
                          mainView.forecast_recyclerview.adapter = ForecastRecyclerView(it,mainView)
                          mweatherforecast = it
                      }, //Load data from cache if an internet problem occurs
                         { throwable -> loadDatafromCache() })
    }

    //We load data in another thread because it's a heavy operation
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