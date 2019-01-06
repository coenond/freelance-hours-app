package com.coen.freelancehours.ui.tax

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coen.freelancehours.R
import com.coen.freelancehours.model.Tax
import kotlinx.android.synthetic.main.item_tax.view.*

class TaxAdapter(private var taxes : ArrayList<Tax>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int { return taxes.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tax, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTaxName.text = taxes[position].name
        holder.tvTaxRate.text = taxes[position].rate.toString()
    }

    fun update(items : ArrayList<Tax>) {
        this.taxes = items
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvTaxName = view.tv_tax_name!!
    val tvTaxRate = view.tv_tax_rate!!
}