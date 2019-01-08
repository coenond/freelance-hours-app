package com.coen.freelancehours.ui.tax.add

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.databinding.FragmentTaxAddBinding
import com.coen.freelancehours.ui.tax.TaxFragment
import kotlinx.android.synthetic.main.fragment_tax_add.*

class TaxAddFragment : BaseFragment<FragmentTaxAddBinding, TaxAddViewModel>() {

    override fun getVMClass(): Class<TaxAddViewModel> = TaxAddViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_tax_add


    /**
     * Initialize newInstance for passing paramters
     */
    companion object {
        fun newInstance(): TaxAddFragment {
            val fragmentHome = TaxAddFragment()
            val args = Bundle()
            fragmentHome.arguments = args

            return fragmentHome
        }
    }

    private fun initObservers() {
        viewModel.status.observe(this, Observer {
            sbMsg("Tax ${viewModel.name.value} added.")
            val fragment = TaxFragment.newInstance()
            addFragment(fragment)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        fab_submit.setOnClickListener {
            viewModel.name.value = et_name.text.toString()
            viewModel.rate.value = et_rate.text.toString().toDouble()

            viewModel.onSubmitClick()
        }
    }
}