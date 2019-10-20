package com.example.mooncascade.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import kotlinx.android.synthetic.main.forecast_layout.view.*

class ForecastRecyclerView(private val forecastweather : ForecastWeather) :
                                           RecyclerView.Adapter<ForecastRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            txttitle.apply{ text =  String.format("Weather forecast for:       %s",
                                                        forecastweather.forecast[position].mdate) }
            txtphenomenon.apply{ text = forecastweather.forecast[position].mday.mphenomenon}
            txttext.apply{ text = forecastweather.forecast[position].mday.mtext}
            txtpeipsi.apply{ text = forecastweather.forecast[position].mday.mpeipsi}
            txttempmin.apply{text = String.format("Temperature range:   %s......",
                                                 forecastweather.forecast[position].mday.mtempmin)}
            txttempmax.apply{ text = forecastweather.forecast[position].mday.mtempmax }
            txtnighttext.apply { text = forecastweather.forecast[position].mnight.mtext }
        }
    }

    override fun getItemCount(): Int {
        return forecastweather.forecast.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txttitle = view.txtdate
        val txtphenomenon = view.txtphenomenon
        val txttext = view.txttext
        val txtpeipsi = view.txtpeipsi
        val txttempmin = view.txttempmin
        val txttempmax = view.txttempmax
        val txtnighttext = view.txtnighttext
    }

}