package com.coen.freelancehours.base

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val isLoading = MutableLiveData<Boolean>()

    protected fun startLoading() { isLoading.value = true }

    protected fun stopLoading() { isLoading.value = false }

}