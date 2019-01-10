package com.coen.freelancehours.ui.hour

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvHourName.text = hours[position].name
        holder.tvTime.text = formatTime(hours[position])
        holder.tvDate.text = formatDate(hours[position])
        holder.tvDescription.text = hours[position].description
        holder.tvRevenue.text = "$" + round(hours[position].cost_incl)
        holder.tvTax.text = "tax rate " + hours[position].tax_rate + "%"
        holder.tvTaxRate.text = "$" + round(hours[position].tax_amount) + " to tax"
    }

    fun update(items : ArrayList<Hour>) {
        this.hours = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Hour? {
        return hours?.get(position)
    }

    fun formatTime(hour: Hour): String {
        var start = hour.started_at.substring(11, 16)
        var end = hour.finished_at.substring(11, 16)

        return "From $start to $end"
    }

    fun formatDate(hour: Hour): String {
        var year = hour.started_at.substring(0, 4)
        var month = hour.started_at.substring(5, 7)
        var day = hour.started_at.substring(8, 10)

        return "At $day-$month-$year"
    }

    private fun round(n: Double): String {
        return String.format("%.2f", n)
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvHourName = view.tv_hour_name!!
    val tvTime = view.tv_time!!
    val tvDate = view.tv_date!!
    val tvDescription = view.tv_description!!
    val tvRevenue = view.tv_revenue!!
    val tvTax = view.tv_tax!!
    val tvTaxRate = view.tv_tax_rate!!
}