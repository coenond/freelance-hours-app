package com.coen.freelancehours.ui.tax

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.LinearLayout
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.model.Tax
import com.coen.freelancehours.databinding.FragmentTaxBinding
import com.coen.freelancehours.ui.tax.add.TaxAddFragment
import com.coen.freelancehours.util.SwipeToDelete
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

        viewModel.taxList?.observe(this, Observer {
            taxAdapter.update(it as ArrayList<Tax>)
        })

        // Set Swipe to delete
        val onSwipe = object : SwipeToDelete(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val tax = taxAdapter.getItem(viewHolder.adapterPosition)!!
                viewModel.deleteTax(tax)
                sbMsg("Tax ${tax.name} deleted")
            }
        }
        val itemTouchHelper = ItemTouchHelper(onSwipe)
        itemTouchHelper.attachToRecyclerView(rv_tax_list)

        fab_add_tax.setOnClickListener {
            val fragment = TaxAddFragment.newInstance()
            addFragment(fragment)
        }
    }
}