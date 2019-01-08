package com.coen.freelancehours.ui.tax

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.coen.freelancehours.api.response.tax.TaxAllResponse
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.model.Tax
import com.coen.freelancehours.repository.TaxRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TaxViewModel(application: Application) : BaseViewModel(application) {

    private var repo: TaxRepository = TaxRepository(application.applicationContext)

    var taxList = repo.getAllTaxes()
    var status = MutableLiveData<String>()

    fun deleteTax(tax: Tax) = repo.deleteTax(tax)

}