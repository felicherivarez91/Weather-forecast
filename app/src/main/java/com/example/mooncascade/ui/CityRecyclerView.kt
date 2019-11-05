package com.example.mooncascade.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mooncascade.R
import com.example.mooncascade.data.ForecastWeather
import kotlinx.android.synthetic.main.cities_items_layout.view.*

/**
 * @author Dmitry Tkachuk
 * Created on 01.11.2019
 * All rights reserved
 */
class CityRecyclerView(private val mweatherforecast : ForecastWeather, val mcontext : MainActivity):
                                               RecyclerView.Adapter<CityRecyclerView.ViewHolder>() {

    private val currday : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cities_items_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        matchdayimg(mweatherforecast.forecast[currday].mday.mplaces[position].mphenomenon , holder)
        matchnightimg(mweatherforecast.forecast[currday].mnight.mplaces[position].mphenomenon , holder)
        with(holder) {
            mcityname.apply { text = mweatherforecast.forecast[currday].mday.mplaces[position].
                                                                                        mplacesname}
            mtempmin.apply {text = String.format("%s......",
                mweatherforecast.forecast[currday].mday.mplaces[position].mtempmin ?:
                                                             resources.getString(R.string.no_info))}
            mtextmax.apply { text = mweatherforecast.forecast[currday].mday.mplaces[position].
                                                                                           mtempmax}
            mtempminnight.apply { text = String.format("%s......",
                mweatherforecast.forecast[currday].mnight.mplaces[position].mtempmin ?:
                                                             resources.getString(R.string.no_info))}
            mtempmaxngiht.apply { text = mweatherforecast.forecast[currday].mnight
                               .mplaces[position].mtempmax ?: resources.getString(R.string.no_info)}

        }
    }
    fun matchdayimg(phenomenon: String, holder: ViewHolder) {
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

    fun matchnightimg(phenomenon : String , holder:ViewHolder){
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
            "Light snow shower" -> {
                holder.imgnight.setImageResource(R.drawable.light_snow_shower)
                holder.txtnightphenomenon.text = mcontext.resources.getString(R.string.light_snow_shower)
            }
            "Moderate snow shower" -> {
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

    override fun getItemCount(): Int = mweatherforecast.forecast[currday].mday.mplaces.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mcityname = mView.txtcityname
        val mtempmin = mView.txtcitytempmin
        val mtextmax = mView.txttempmax
        val mphenomenon = mView.txtphenomenon
        val imgday = mView.imgday
        val mtempminnight = mView.txttempminnight
        val mtempmaxngiht = mView.txttempmaxnight
        val txtnightphenomenon = mView.txtnightphenomenon
        val imgnight = mView.imgnight
    }

}
