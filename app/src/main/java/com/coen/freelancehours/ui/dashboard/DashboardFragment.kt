package com.coen.freelancehours.ui.dashboard

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.databinding.FragmentDashboardBinding
import com.coen.freelancehours.model.Dashboard

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override fun getVMClass(): Class<DashboardViewModel> = DashboardViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_dashboard

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): DashboardFragment {
            val fragmentHome = DashboardFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dashboardData.observe(this, Observer {
            setValues(it, view)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setValues(dashboardData: Dashboard?, view: View) {
        val revenue = view.findViewById(R.id.tv_tr_value) as TextView
        val projects = view.findViewById(R.id.tv_tp_value) as TextView
        val hours = view.findViewById(R.id.tv_th_value) as TextView
        val logs = view.findViewById(R.id.tv_tl_value) as TextView

        revenue.text = "$${dashboardData?.total_revenue}"
        projects.text = "$${dashboardData?.total_projects}"
        hours.text = "$${dashboardData?.total_hours}"
        logs.text = "$${dashboardData?.total_logs}"
    }
}