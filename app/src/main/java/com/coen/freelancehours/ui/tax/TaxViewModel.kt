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

    private var repo: TaxRepository = TaxRepository()

    var taxList = MutableLiveData<ArrayList<Tax>>()

    init {
        getAllTaxes()
    }

    fun setData(taxList: ArrayList<Tax>) {
        this.taxList.value = taxList
    }

    private fun getAllTaxes() {
        repo.getAllTaxes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TaxAllResponse> {
                    override fun onSuccess(response: TaxAllResponse) {
                        response.taxes?.let {
                            var list = ArrayList<Tax>()
                            list.addAll(it)
                            taxList.postValue(list)
                        }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) { Log.i("TAGZ", "OnSubscribe!") }
                })
    }

}