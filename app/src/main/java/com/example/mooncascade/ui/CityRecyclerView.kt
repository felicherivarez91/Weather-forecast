package com.example.mooncascade.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather

import kotlinx.android.synthetic.main.cities_items_layout.view.*

class CityRecyclerView(private val mweatherforecast : ForecastWeather, val mcontext : MainActivity):
                                               RecyclerView.Adapter<CityRecyclerView.ViewHolder>() {

    private val currday : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cities_items_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            mcityname.apply { text = mweatherforecast.forecast[currday].mday.mplaces[position].
                                                                                        mplacesname}
            mtempmin.apply {text = String.format("Temperature range:   %s......",
                               mweatherforecast.forecast[currday].mday.mplaces[position].mtempmin) }
        }
    }

    override fun getItemCount(): Int = mweatherforecast.forecast[currday].mday.mplaces.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mcityname = mView.txtcityname
        val mtempmin = mView.txtcitytempmin
        val mtextmax = mView.txttempmax
    }

}
