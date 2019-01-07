package com.coen.freelancehours.ui.project

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coen.freelancehours.R
import com.coen.freelancehours.model.Project
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(private val onClickCallback: (Project?) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private var projects: ArrayList<Project>? = null
    private lateinit var context: Context

    override fun getItemCount(): Int {return projects?.size ?: 0 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvProjectName.text = projects!![position].name
            tvHourRate.text = projects!![position].hourRate.toString()

            cvProject.setOnClickListener{
                onClickCallback(projects?.get(position))
            }
        }
    }

    fun update(items : ArrayList<Project>) {
        this.projects = items
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvProjectName = view.tv_project_name!!
    val tvHourRate = view.tv_hour_rate!!
    val cvProject = view.cv_project!!
}