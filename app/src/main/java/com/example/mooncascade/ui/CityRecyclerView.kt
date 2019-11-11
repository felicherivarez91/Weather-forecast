package com.example.mooncascade.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.interfaces.BindableAdapter
import kotlinx.android.synthetic.main.cities_items_layout.view.*
import kotlinx.android.synthetic.main.cities_items_layout.view.imgday
import kotlinx.android.synthetic.main.cities_items_layout.view.imgnight
import kotlinx.android.synthetic.main.cities_items_layout.view.txtnightphenomenon
import kotlinx.android.synthetic.main.cities_items_layout.view.txtphenomenon
import kotlinx.android.synthetic.main.cities_items_layout.view.txttempmax

/**
 * @author Dmitry Tkachuk
 * Created on 01.11.2019
 * All rights reserved
 */
const val CURRENT_DAY = 0

class CityRecyclerView(private val mcontext : MainActivity):
                                               RecyclerView.Adapter<CityRecyclerView.ViewHolder>(),
                                                                   BindableAdapter<ForecastWeather>{

    private lateinit var mweatherforecast : ForecastWeather

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cities_items_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        matchdayimg(mweatherforecast.forecast[CURRENT_DAY].mday.mplaces[position].mphenomenon , holder)
        matchnightimg(mweatherforecast.forecast[CURRENT_DAY].mnight.mplaces[position].mphenomenon , holder)
        holder.bind(mweatherforecast,position)
    }

    private fun matchdayimg(phenomenon: String, holder: ViewHolder) {
        when (phenomenon) {
            "Clear" -> {
                holder.mphenomenon.text = mcontext.resources.getString(R.string.clear)
                holder.imgday.setImageResource(R.drawable.clear)
            }
            "Few clouds" -> {
                holder.imgday.setImageResource(R.drawable.few_clouds)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.few_clouds)
            }
            "Variable clouds" -> {
                holder.imgday.setImageResource(R.drawable.variable_clouds)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.variable_clouds)
            }
            "Cloudy with clear spells" -> {
                holder.mphenomenon.text = mcontext.resources.getString(R.string.cloudy_with_clear_spells)
                holder.imgday.setImageResource(R.drawable.variable_clouds)
            }
            "Cloudy" -> {
                holder.imgday.setImageResource(R.drawable.cloudy)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.cloudy)
            }
            "Light rain" -> {
                holder.imgday.setImageResource(R.drawable.light_rain)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.light_rain)
            }
            "Moderate rain" -> {
                holder.imgday.setImageResource(R.drawable.moderate_rain)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.moderate_rain)
            }
            "Heavy rain" -> {
                holder.mphenomenon.text = mcontext.resources.getString(R.string.heavy_rain)
                holder.imgday.setImageResource(R.drawable.moderate_rain)
            }
            "Light snowfall", "Light snow shower" -> {
                holder.imgday.setImageResource(R.drawable.light_snow_shower)
                holder.mphenomenon.text =
                    mcontext.resources.getString(R.string.light_snow_shower)
            }
            "Moderate snowfall","Moderate snow shower" -> {
                holder.imgday.setImageResource(R.drawable.moderate_snow)
                holder.mphenomenon.text =
                   mcontext.resources.getString(R.string.moderate_snow_shower)
            }
            "Light shower" -> {
                holder.imgday.setImageResource(R.drawable.light_shower)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.light_shower)
            }
            "Moderate shower" -> {
                holder.imgday.setImageResource(R.drawable.light_shower)
                holder.mphenomenon.text =
                    mcontext.resources.getString(R.string.moderate_shower)
            }
            "Heavy shower" -> {
                holder.imgday.setImageResource(R.drawable.light_shower)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.heavy_shower)
            }
            "Hail" -> {
                holder.imgday.setImageResource(R.drawable.hail)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.hail)
            }
            "Light sleet" -> {
                holder.mphenomenon.text = mcontext.resources.getString(R.string.light_sleet)
                holder.imgday.setImageResource(R.drawable.sleet)
            }
            "Moderate sleet" -> {
                holder.mphenomenon.text = mcontext.resources.getString(R.string.moderate_sleet)
                holder.imgday.setImageResource(R.drawable.sleet)
            }
            "Mist" -> {
                holder.imgday.setImageResource(R.drawable.mist)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.mist)
            }
            "Fog" -> {
                holder.imgday.setImageResource(R.drawable.fog)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.fog)
            }
            "Thunderstorm" -> {
                holder.imgday.setImageResource(R.drawable.thunderstorm)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.thunderstorm)
            }
            "Thunder" -> {
                holder.imgday.setImageResource(R.drawable.thunder)
                holder.mphenomenon.text = mcontext.resources.getString(R.string.thunder)
            }
            else -> holder.imgday.setImageResource(R.drawable.noinfo)
        }
    }

    private fun matchnightimg(phenomenon : String , holder:ViewHolder){
        when (phenomenon){
            "Clear" ->{
                holder.imgnight.setImageResource(R.drawable.cloudy_moon)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.clear)
            }
            "Few clouds" -> {
                holder.imgnight.setImageResource(R.drawable.few_clouds)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.few_clouds)
            }
            "Variable clouds" ->{
                holder.imgnight.setImageResource(R.drawable.night_variable_clouds)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.variable_clouds)

            }
            "Cloudy with clear spells" ->{
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.cloudy_with_clear_spells)
                holder.imgnight.setImageResource(R.drawable.night_variable_clouds)
            }

            "Cloudy" -> {
                holder.imgnight.setImageResource(R.drawable.cloudy)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.cloudy)
            }
            "Light rain" ->{
                holder.imgnight.setImageResource(R.drawable.light_rain)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.light_rain)
            }
            "Moderate rain"->{
                holder.imgnight.setImageResource(R.drawable.moderate_rain)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.moderate_rain)
            }
            "Heavy rain"-> {
                holder.imgnight.setImageResource(R.drawable.moderate_rain)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.heavy_rain)
            }
            "Light shower" ->{
                holder.imgnight.setImageResource(R.drawable.heavy_shower)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.light_shower)
            }
            "Moderate shower" ->{
                holder.imgnight.setImageResource(R.drawable.night_shower)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.moderate_shower)
            }
            "Heavy shower"-> {
                holder.imgnight.setImageResource(R.drawable.night_shower)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.heavy_shower)
            }
            "Light snowfall","Light snow shower" -> {
                holder.imgnight.setImageResource(R.drawable.light_snow_shower)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.light_snow_shower)
            }
            "Moderate snowfall","Moderate snow shower" -> {
                holder.imgnight.setImageResource(R.drawable.moderate_snow)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.moderate_snow_shower)
            }
            "Light sleet"->{
                holder.imgnight.setImageResource(R.drawable.sleet)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.light_sleet)
            }
            "Moderate sleet" -> {
                holder.imgnight.setImageResource(R.drawable.sleet)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.moderate_sleet)
            }
            "Hail" -> {
                holder.imgnight.setImageResource(R.drawable.hail)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.hail)
            }
            "Mist" -> {
                holder.imgnight.setImageResource(R.drawable.mist)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.mist)
            }
            "Fog" -> {
                holder.imgnight.setImageResource(R.drawable.fog)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.fog)
            }
            "Thunderstorm" -> {
                holder.imgnight.setImageResource(R.drawable.thunderstorm)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.thunderstorm)
            }
            "Thunder" -> {
                holder.imgnight.setImageResource(R.drawable.thunder)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.thunder)
            }
            else -> holder.imgnight.setImageResource(R.drawable.noinfo)
        }
    }

    override fun getItemCount(): Int = mweatherforecast.forecast[CURRENT_DAY].mday.mplaces.size

    override fun setData(items: ForecastWeather) {
        mweatherforecast = items
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        fun bind(forecastweather: ForecastWeather,position: Int) {
            with(itemView){
                txtcityname.apply{text = forecastweather.forecast[CURRENT_DAY].mday.mplaces[position].
                                                                                        mplacesname}
                txtcitytempmin.apply{text = String.format("%s......",
                            forecastweather.forecast[CURRENT_DAY].mday.mplaces[position].mtempmin ?:
                                                             resources.getString(R.string.no_info))}
                txttempmax.apply{text = forecastweather.forecast[CURRENT_DAY].mday.mplaces[position].mtempmax}
                txttempminnight.apply{text = String.format("%s......",
                    forecastweather.forecast[CURRENT_DAY].mnight.mplaces[position].mtempmin ?:
                                                             resources.getString(R.string.no_info))}
                txttempmaxnight.apply{text = forecastweather.forecast[CURRENT_DAY].mnight
                    .mplaces[position].mtempmax ?: resources.getString(R.string.no_info)}
            }
        }
        val mphenomenon = mView.txtphenomenon
        val imgday = mView.imgday
        val txtnightphenomenon = mView.txtnightphenomenon
        val imgnight = mView.imgnight
    }

}