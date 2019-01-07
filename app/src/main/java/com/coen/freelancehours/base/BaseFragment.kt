package com.coen.freelancehours.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.coen.freelancehours.R

abstract class BaseFragment<T : ViewDataBinding, V : ViewModel> : Fragment() {

    lateinit var binding: T
    lateinit var viewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewModel = ViewModelProviders.of(this).get(getVMClass())
        initViewModelBinding()

        return binding.root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initViewModelBinding()

    abstract fun getVMClass(): Class<V>

    fun sbMsg(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

    /**
     * add/replace fragment in container [framelayout]
     */
    @SuppressLint("PrivateResource")
    fun addFragment(fragment: Fragment) {
        val manager = fragmentManager
        val transaction = manager!!
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.design_bottom_sheet_slide_in,
                        R.anim.design_bottom_sheet_slide_out
                )
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)

        transaction.commit()
    }
}