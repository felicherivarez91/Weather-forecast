package com.example.mooncascade.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.R
import com.example.mooncascade.interfaces.BasePresenter
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.databinding.ActivityMainBinding
import com.example.mooncascade.interfaces.BindableAdapter
import com.example.mooncascade.ui.ForecastRecyclerView
import com.example.mooncascade.ui.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: T) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).run { setData(items) }
    }
}

class MainPresenter: BasePresenter<MainActivity> {

    private lateinit var mainView: MainActivity
    private var disposable : Disposable? = null
    @Volatile private var mweatherforecast : ForecastWeather? = null
    var mbinding : ActivityMainBinding? = null

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
                     .subscribe({
                         mainView.forecast_recyclerview.layoutManager = LinearLayoutManager(mainView)
                         mainView.forecast_recyclerview.adapter = ForecastRecyclerView(mainView)
                         mbinding!!.forecast = it
                         mweatherforecast = it
                     },
                         //Load data from cache if an internet problem occurs
                         {
                             loadDatafromCache()
                         })
    }

    //We load data in another thread because it's a heavy operation
    override fun loadDatafromCache() {
        Thread {
            mweatherforecast = mainView.forecastDataBase.forecastdao().getforecastWeather()
            mainView.runOnUiThread{
                mainView.forecast_recyclerview.layoutManager = LinearLayoutManager(mainView)
                if (mweatherforecast == null){
                    Toast.makeText(mainView, R.string.no_database,LENGTH_LONG).show()
                }
                else{
                    mbinding!!.forecast = mweatherforecast
                    mainView.forecast_recyclerview.adapter = ForecastRecyclerView(mainView)
                }
            }
        }.start()
    }

    override fun setbinding(binding: ActivityMainBinding) {
          mbinding = binding
    }

    override fun onDetach() {
          disposable?.dispose()
    }

}