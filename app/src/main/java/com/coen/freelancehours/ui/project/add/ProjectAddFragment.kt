package com.coen.freelancehours.ui.project.add

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.databinding.FragmentProjectAddBinding
import com.coen.freelancehours.ui.project.ProjectFragment
import kotlinx.android.synthetic.main.fragment_project_add.*

class ProjectAddFragment : BaseFragment<FragmentProjectAddBinding, ProjectAddViewModel>() {

    override fun getVMClass(): Class<ProjectAddViewModel> = ProjectAddViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_project_add


    /**
     * Initialize newInstance for passing paramters
     */
    companion object {
        fun newInstance(): ProjectAddFragment {
            val fragmentHome = ProjectAddFragment()
            val args = Bundle()
            fragmentHome.arguments = args

            return fragmentHome
        }
    }

    private fun initObservers() {
        viewModel.status.observe(this, Observer {
            sbMsg("Project ${viewModel.name.value} added. ${viewModel.status.value}")
            val fragment = ProjectFragment.newInstance()
            addFragment(fragment)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        fab_submit.setOnClickListener {
            viewModel.name.value = et_name.text.toString()
            viewModel.hour_rate.value = et_hour_rate.text.toString().toDouble()

            viewModel.onSubmitClick()
        }
    }
}