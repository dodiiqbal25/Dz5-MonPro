package com.example.dhelloworld

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_dashboard.view.*
import kotlinx.android.synthetic.main.item_dasboard.view.*

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateTime = itemView.day
    private val description = itemView.desc
    private val tempNow = itemView.temp
    private val tempMinMax = itemView.temp_range
    private  val imageIcon = itemView.icon


    fun bindItem(item: ListItem) {
        dateTime.text = item.dt?.let { Util.getDayName(it) }
        description.text = item.weather?.get(0)?.description
        tempNow.text = item.main?.temp?.let { Util.setFormatTemperature(it) }
        tempMinMax.text = item.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item.main?.tempMax?.let {Util.setFormatTemperature(it)}
        item?.weather?.get(0)?.id?.let { Util.getArtResourceForWeatherCondition(it) }?.let {  imageIcon.setImageResource(it)}


    }
}