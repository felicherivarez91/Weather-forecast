package com.example.mooncascade.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.interfaces.BindableAdapter
import kotlinx.android.synthetic.main.forecast_layout.view.*

/**
 * @author Dmitry Tkachuk
 * Created on 21.10.2019
 * All rights reserved
 */
class ForecastRecyclerView(private val context : MainActivity) :
                                            RecyclerView.Adapter<ForecastRecyclerView.ViewHolder>(),
                                             BindableAdapter<ForecastWeather>{

    lateinit var  forecastweather: ForecastWeather

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        matchdayimg(forecastweather.forecast[position].mday.mphenomenon , holder)
        matchnightimg(forecastweather.forecast[position].mnight.mphenomenon , holder)
        holder.bind(forecastweather,position , context)
    }

    private fun matchdayimg(phenomenon: String, holder: ViewHolder) {
            when (phenomenon) {
                "Clear" -> {
                    holder.txtphenomenon.text = context.resources.getString(R.string.clear)
                    holder.imgday.setImageResource(R.drawable.clear)
                }
                "Few clouds" -> {
                    holder.imgday.setImageResource(R.drawable.few_clouds)
                    holder.txtphenomenon.text = context.resources.getString(R.string.few_clouds)
                }
                "Variable clouds" -> {
                    holder.imgday.setImageResource(R.drawable.variable_clouds)
                    holder.txtphenomenon.text = context.resources.getString(R.string.variable_clouds)
                }
                "Cloudy with clear spells" -> {
                    holder.txtphenomenon.text = context.resources.getString(R.string.cloudy_with_clear_spells)
                    holder.imgday.setImageResource(R.drawable.variable_clouds)
                }
                "Cloudy" -> {
                    holder.imgday.setImageResource(R.drawable.cloudy)
                    holder.txtphenomenon.text = context.resources.getString(R.string.cloudy)
                }
                "Light rain" -> {
                    holder.imgday.setImageResource(R.drawable.light_rain)
                    holder.txtphenomenon.text = context.resources.getString(R.string.light_rain)
                }
                "Moderate rain" -> {
                    holder.imgday.setImageResource(R.drawable.moderate_rain)
                    holder.txtphenomenon.text = context.resources.getString(R.string.moderate_rain)
                }
                "Heavy rain" -> {
                    holder.txtphenomenon.text = context.resources.getString(R.string.heavy_rain)
                    holder.imgday.setImageResource(R.drawable.moderate_rain)
                }
                "Light snowfall","Light snow shower" -> {
                    holder.imgday.setImageResource(R.drawable.light_snow_shower)
                    holder.txtphenomenon.text = context.resources.getString(R.string.light_snow_shower)
                }
                "Moderate snowfall","Moderate snow shower" -> {
                    holder.imgday.setImageResource(R.drawable.moderate_snow)
                    holder.txtphenomenon.text = context.resources.getString(R.string.moderate_snow_shower)
                }
                "Light shower" -> {
                    holder.imgday.setImageResource(R.drawable.light_shower)
                    holder.txtphenomenon.text = context.resources.getString(R.string.light_shower)
                }
                "Moderate shower" -> {
                    holder.imgday.setImageResource(R.drawable.light_shower)
                    holder.txtphenomenon.text = context.resources.getString(R.string.moderate_shower)
                }
                "Heavy shower" -> {
                    holder.imgday.setImageResource(R.drawable.light_shower)
                    holder.txtphenomenon.text = context.resources.getString(R.string.heavy_shower)
                }
                "Hail" -> {
                    holder.imgday.setImageResource(R.drawable.hail)
                    holder.txtphenomenon.text = context.resources.getString(R.string.hail)
                }
                "Light sleet" -> {
                    holder.txtphenomenon.text = context.resources.getString(R.string.light_sleet)
                    holder.imgday.setImageResource(R.drawable.sleet)
                }
                "Moderate sleet" -> {
                    holder.txtphenomenon.text = context.resources.getString(R.string.moderate_sleet)
                    holder.imgday.setImageResource(R.drawable.sleet)
                }
                "Mist" -> {
                    holder.imgday.setImageResource(R.drawable.mist)
                    holder.txtphenomenon.text = context.resources.getString(R.string.mist)
                }
                "Fog" -> {
                    holder.imgday.setImageResource(R.drawable.fog)
                    holder.txtphenomenon.text = context.resources.getString(R.string.fog)
                }
                "Thunderstorm" -> {
                    holder.imgday.setImageResource(R.drawable.thunderstorm)
                    holder.txtphenomenon.text = context.resources.getString(R.string.thunderstorm)
                }
                "Thunder" -> {
                    holder.imgday.setImageResource(R.drawable.thunder)
                    holder.txtphenomenon.text = context.resources.getString(R.string.thunder)
                }
                else -> holder.imgday.setImageResource(R.drawable.noinfo)
            }
    }

    private fun matchnightimg(phenomenon : String , holder: ViewHolder){
        when (phenomenon){
            "Clear" ->{
                holder.imgnight.setImageResource(R.drawable.cloudy_moon)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.clear)
            }
            "Few clouds" -> {
                holder.imgnight.setImageResource(R.drawable.few_clouds)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.few_clouds)
            }
            "Variable clouds" ->{
                holder.imgnight.setImageResource(R.drawable.night_variable_clouds)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.variable_clouds)

            }
            "Cloudy with clear spells" ->{
                holder.txtnightphenomenon.text = context.resources.getString(R.string.cloudy_with_clear_spells)
                holder.imgnight.setImageResource(R.drawable.night_variable_clouds)
            }

            "Cloudy" -> {
                holder.imgnight.setImageResource(R.drawable.cloudy)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.cloudy)
            }
            "Light rain" ->{
                holder.imgnight.setImageResource(R.drawable.light_rain)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.light_rain)
            }
            "Moderate rain"->{
                holder.imgnight.setImageResource(R.drawable.moderate_rain)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.moderate_rain)
            }
            "Heavy rain"-> {
                holder.imgnight.setImageResource(R.drawable.moderate_rain)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.heavy_rain)
            }
            "Light shower" ->{
                holder.imgnight.setImageResource(R.drawable.heavy_shower)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.light_shower)
            }
            "Moderate shower" ->{
                holder.imgnight.setImageResource(R.drawable.night_shower)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.moderate_shower)
            }
            "Heavy shower"-> {
                holder.imgnight.setImageResource(R.drawable.night_shower)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.heavy_shower)
            }
            "Light snowfall", "Light snow shower" -> {
                holder.imgnight.setImageResource(R.drawable.light_snow_shower)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.light_snow_shower)
            }
            "Moderate snowfall","Moderate snow shower" -> {
                holder.imgnight.setImageResource(R.drawable.moderate_snow)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.moderate_snow_shower)
            }
            "Light sleet"->{
                holder.imgnight.setImageResource(R.drawable.sleet)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.light_sleet)
            }
            "Moderate sleet" -> {
                holder.imgnight.setImageResource(R.drawable.sleet)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.moderate_sleet)
            }
            "Hail" -> {
                holder.imgnight.setImageResource(R.drawable.hail)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.hail)
            }
            "Mist" -> {
                holder.imgnight.setImageResource(R.drawable.mist)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.mist)
            }
            "Fog" -> {
                holder.imgnight.setImageResource(R.drawable.fog)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.fog)
            }
            "Thunderstorm" -> {
                holder.imgnight.setImageResource(R.drawable.thunderstorm)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.thunderstorm)
            }
            "Thunder" -> {
                holder.imgnight.setImageResource(R.drawable.thunder)
                holder.txtnightphenomenon.text = context.resources.getString(R.string.thunder)
            }
            else -> holder.imgnight.setImageResource(R.drawable.noinfo)
        }
    }

    override fun getItemCount(): Int {
        return forecastweather.forecast.size
    }

    override fun setData(items: ForecastWeather) {
      forecastweather = items
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(forecastweather: ForecastWeather,position: Int , context: MainActivity) {
            with(itemView){
                txtdate.text = forecastweather.forecast[position].mdate
                txttext.text = forecastweather.forecast[position].mday.mtext
                txtpeipsi.apply{text = forecastweather.forecast[position].mday.mpeipsi ?:
                                                              resources.getString(R.string.no_info)}
                txttempmin.apply{text = String.format("%s......",
                                                 forecastweather.forecast[position].mday.mtempmin)}
                txttempmax.apply{text = forecastweather.forecast[position].mday.mtempmax }
                txtsea.apply{ text = forecastweather.forecast[position].mday.msea ?:
                                                              resources.getString(R.string.no_info)}
                txtnighttext.apply{text = forecastweather.forecast[position].mnight.mtext }
                txtnighttempmin.apply{text = String.format("%s......",
                    forecastweather.forecast[position].mnight.mtempmin)}
                txtnighttempmax.apply { text = forecastweather.forecast[position].mnight.mtempmax }
                txtnightpeipsi.apply { text = forecastweather.forecast[position].mnight.mpeipsi ?:
                                                            resources.getString(R.string.no_info) }
                txtnightsea.apply { text = forecastweather.forecast[position].mnight.msea ?:
                                                             resources.getString(R.string.no_info) }
                setOnClickListener {context.onItemforecastClicked(forecastweather,position)  }
            }
        }

        val txtphenomenon = view.txtphenomenon
        val imgday = view.imgday
        val txtnightphenomenon = view.txtnightphenomenon
        val imgnight = view.imgnight

    }
}