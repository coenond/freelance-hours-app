package com.coen.freelancehours.ui.hour

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coen.freelancehours.R
import com.coen.freelancehours.model.Hour
import kotlinx.android.synthetic.main.item_hour.view.*

class HourAdapter(private var hours : ArrayList<Hour>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int { return hours.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hour, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvHourtName.text = hours[position].name
        holder.tvHourRate.text = hours[position].cost_incl.toString()
    }

    fun update(items : ArrayList<Hour>) {
        this.hours = items
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvHourtName = view.tv_hour_name!!
    val tvHourRate = view.tv_hour_rate!!
}