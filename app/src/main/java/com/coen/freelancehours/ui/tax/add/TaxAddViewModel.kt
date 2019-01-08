package com.coen.freelancehours.ui.tax.add

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.repository.TaxRepository

class TaxAddViewModel(application: Application) : BaseViewModel(application) {

    private var repo: TaxRepository = TaxRepository(application.applicationContext)

    var name = MutableLiveData<String>()
    var rate = MutableLiveData<Double>()

    var status = MutableLiveData<String>()
    var message = MutableLiveData<String>()

    fun onSubmitClick() {
        repo.storeTax(name.value!!, rate.value!!)

        /* TODO: make this dynamic with the response */
        status.value = "success"
    }
}
