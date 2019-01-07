package com.coen.freelancehours.ui.project

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coen.freelancehours.R
import com.coen.freelancehours.model.Project
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(private var projects : ArrayList<Project>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int { return projects.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProjectName.text = projects[position].name
        holder.tvHourRate.text = projects[position].hourRate.toString()
    }

    fun update(items : ArrayList<Project>) {
        this.projects = items
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvProjectName = view.tv_project_name!!
    val tvHourRate = view.tv_hour_rate!!
}