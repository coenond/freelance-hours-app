package com.coen.freelancehours.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>() : AppCompatActivity() {
    lateinit var binding: VDB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProviders.of(this).get(getVMClass())
        initVMBinding()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initVMBinding()

    abstract fun getVMClass(): Class<VM>
}