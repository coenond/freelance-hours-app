package com.coen.freelancehours.base

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
}