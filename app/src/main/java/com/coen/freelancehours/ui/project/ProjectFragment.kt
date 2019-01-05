package com.coen.freelancehours.ui.project

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.databinding.FragmentProjectBinding
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {

    override fun getVMClass(): Class<ProjectViewModel> = ProjectViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_project

    lateinit var projectAdapter: ProjectAdapter
//    private lateinit var freelanceHoursApiService: FreelanceHoursApiService
    var projects = ArrayList<Project>()

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): ProjectFragment {
            val fragmentHome = ProjectFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init RecyclerView
        projectAdapter = ProjectAdapter(projects, this.context!!)

        rv_project_list.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_project_list.adapter = projectAdapter

        viewModel.projectList.observe(this, Observer {
            projectAdapter.update(it as ArrayList<Project>)
        })

    }

    fun sbMsg(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}