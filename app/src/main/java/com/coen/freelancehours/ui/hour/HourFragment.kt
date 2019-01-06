package com.coen.freelancehours.ui.hour

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.model.Hour
import com.coen.freelancehours.databinding.FragmentHourBinding
import kotlinx.android.synthetic.main.fragment_hour.*

class HourFragment : BaseFragment<FragmentHourBinding, HourViewModel>() {

    override fun getVMClass(): Class<HourViewModel> = HourViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_hour

    private lateinit var hourAdapter: HourAdapter
    private var hours = ArrayList<Hour>()

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): HourFragment {
            val fragmentHome = HourFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init RecyclerView
        hourAdapter = HourAdapter(hours, this.context!!)

        rv_hour_list.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_hour_list.adapter = hourAdapter

        viewModel.hourList.observe(this, Observer {
            hourAdapter.update(it as ArrayList<Hour>)
        })
    }
}