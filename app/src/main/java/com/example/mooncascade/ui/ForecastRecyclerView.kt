package com.example.mooncascade.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import kotlinx.android.synthetic.main.forecast_layout.view.*

class ForecastRecyclerView(private val forecastweather: ForecastWeather) :
                                           RecyclerView.Adapter<ForecastRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        matchdayimg(forecastweather.forecast[position].mday.mphenomenon , holder)
        matchnightimg(forecastweather.forecast[position].mnight.mphenomenon , holder)

        with(holder){
            txttitle.apply{ text =  String.format("Weather forecast for:       %s",
                                                        forecastweather.forecast[position].mdate) }
            txtphenomenon.apply{ text = forecastweather.forecast[position].mday.mphenomenon}
            txttext.apply{ text = forecastweather.forecast[position].mday.mtext}
            txtpeipsi.apply{ text = forecastweather.forecast[position].mday.mpeipsi}
            txttempmin.apply{text = String.format("Temperature range:   %s......",
                                                 forecastweather.forecast[position].mday.mtempmin)}
            txttempmax.apply{ text = forecastweather.forecast[position].mday.mtempmax }
            txtsea.apply { text = forecastweather.forecast[position].mday.msea  }
            txtnighttext.apply{ text = forecastweather.forecast[position].mnight.mtext }
            txtnightphenomenon.apply{ text = forecastweather.forecast[position].mnight.mphenomenon }
            txtnighttempmin.apply{text = String.format("Temperature range:   %s......",
                                                forecastweather.forecast[position].mnight.mtempmin)}
            txtnighttempmax.apply { text = forecastweather.forecast[position].mnight.mtempmax }
            txtnightpeipsi.apply { text = forecastweather.forecast[position].mnight.mpeipsi }
            txtnightsea.apply { text = forecastweather.forecast[position].mnight.msea }
        }
    }

    private fun matchdayimg(phenomenon : String , holder: ViewHolder) {
        when (phenomenon){
            "Clear" -> holder.imgday.setImageResource(R.drawable.clear)
            "Few clouds" -> holder.imgday.setImageResource(R.drawable.few_clouds)
            "Variable clouds", "Cloudy with clear spells" ->
                                          holder.imgday.setImageResource(R.drawable.variable_clouds)
            "Cloudy" ->  holder.imgday.setImageResource(R.drawable.cloudy)
            "Light rain"-> holder.imgday.setImageResource(R.drawable.light_rain)
            "Moderate rain" , "Heavy rain"
                                         -> holder.imgday.setImageResource(R.drawable.moderate_rain)
            "Light snow shower" -> holder.imgday.setImageResource(R.drawable.light_snow_shower)
            "Moderate snow shower" -> holder.imgday.setImageResource(R.drawable.moderate_snow)
            "Light shower", "Moderate shower", "Heavy shower"
                                          -> holder.imgday.setImageResource(R.drawable.light_shower)
            "Hail" -> holder.imgday.setImageResource(R.drawable.hail)
            "Light sleet","Moderate sleet" -> holder.imgday.setImageResource(R.drawable.sleet)
            "Mist" ->  holder.imgday.setImageResource(R.drawable.mist)
            "Fog" ->  holder.imgday.setImageResource(R.drawable.fog)
            "Thunderstorm" -> holder.imgday.setImageResource(R.drawable.thunderstorm)
            "Thunder" ->  holder.imgday.setImageResource(R.drawable.thunder)
            else -> holder.imgday.setImageResource(R.drawable.noinfo)
        }
    }

    private fun matchnightimg(phenomenon : String , holder: ViewHolder){
        when (phenomenon){
            "Clear" -> holder.imgnight.setImageResource(R.drawable.cloudy_moon)
            "Few clouds" -> holder.imgnight.setImageResource(R.drawable.few_clouds)
            "Variable clouds", "Cloudy with clear spells" ->
                holder.imgnight.setImageResource(R.drawable.night_variable_clouds)
            "Cloudy" ->  holder.imgnight.setImageResource(R.drawable.cloudy)
            "Light rain" -> holder.imgnight.setImageResource(R.drawable.light_rain)
            "Moderate rain","Heavy rain" -> holder.imgnight.setImageResource(R.drawable.moderate_rain)
            "Light shower" ->holder.imgnight.setImageResource(R.drawable.heavy_shower)
            "Moderate shower","Heavy shower"-> holder.imgnight.setImageResource(R.drawable.night_shower)
            "Light snow shower" -> holder.imgnight.setImageResource(R.drawable.light_snow_shower)
            "Moderate snow shower" -> holder.imgnight.setImageResource(R.drawable.moderate_snow)
            "Light sleet","Moderate sleet" -> holder.imgnight.setImageResource(R.drawable.sleet)
            "Hail" -> holder.imgnight.setImageResource(R.drawable.hail)
            "Mist" -> holder.imgnight.setImageResource(R.drawable.mist)
            "Fog" -> holder.imgnight.setImageResource(R.drawable.fog)
            "Thunderstorm" -> holder.imgnight.setImageResource(R.drawable.thunderstorm)
            "Thunder" -> holder.imgnight.setImageResource(R.drawable.thunder)
            else -> holder.imgnight.setImageResource(R.drawable.noinfo)
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
        val imgday = view.imgday
        val txttempmin = view.txttempmin
        val txttempmax = view.txttempmax
        val txtsea = view.txtsea
        val txtnightphenomenon = view.txtnightphenomenon
        val txtnighttempmin = view.txtnighttempmin
        val txtnighttempmax = view.txtnighttempmax
        val imgnight = view.imgnight
        val txtnightsea = view.txtnightsea
        val txtnighttext = view.txtnighttext
        val txtnightpeipsi = view.txtnightpeipsi
    }

}