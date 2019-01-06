package com.coen.freelancehours.ui.tax

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.model.Tax
import com.coen.freelancehours.databinding.FragmentTaxBinding
import kotlinx.android.synthetic.main.fragment_tax.*

class TaxFragment : BaseFragment<FragmentTaxBinding, TaxViewModel>() {

    override fun getVMClass(): Class<TaxViewModel> = TaxViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_tax

    private lateinit var taxAdapter: TaxAdapter
    private var taxes = ArrayList<Tax>()
    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): TaxFragment {
            val fragmentHome = TaxFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init RecyclerView
        taxAdapter = TaxAdapter(taxes, this.context!!)

        rv_tax_list.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_tax_list.adapter = taxAdapter

        viewModel.taxList.observe(this, Observer {
            taxAdapter.update(it as ArrayList<Tax>)
        })
    }
}