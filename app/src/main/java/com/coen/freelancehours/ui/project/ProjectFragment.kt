package com.coen.freelancehours.ui.project

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.LinearLayout
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.databinding.FragmentProjectBinding
import com.coen.freelancehours.ui.project.add.ProjectAddFragment
import com.coen.freelancehours.util.SwipeToDelete
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {

    override fun getVMClass(): Class<ProjectViewModel> = ProjectViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_project

    private val projectAdapter = ProjectAdapter { if (it != null) onProjectClick(it) }
    private var projects = ArrayList<Project>()

    /**
     * Initialize newInstance for passing paramters
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
        projectAdapter.update(projects)

        rv_project_list.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_project_list.adapter = projectAdapter

        viewModel.projectList?.observe(this, Observer {
            projectAdapter.update(it as ArrayList<Project>)
        })

        // Set Swipe to delete
        val onSwipe = object : SwipeToDelete(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val project = projectAdapter.getItem(viewHolder.adapterPosition)!!
                viewModel.deleteProject(project)
                sbMsg("Project ${project.name} deleted")
            }
        }
        val itemTouchHelper = ItemTouchHelper(onSwipe)
        itemTouchHelper.attachToRecyclerView(rv_project_list)

        fab_add_project.setOnClickListener {
            val fragment = ProjectAddFragment.newInstance()
            addFragment(fragment)
        }
    }

    private fun onProjectClick(project: Project) {
        sbMsg("Project ${project.name} clicked")
    }
}