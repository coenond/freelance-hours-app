package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.api.response.tax.TaxAllResponse
import com.coen.freelancehours.api.response.tax.TaxSingleResponse
import com.coen.freelancehours.database.tax.TaxDAO
import com.coen.freelancehours.database.tax.TaxDatabase
import com.coen.freelancehours.model.Tax
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class TaxRepository(context: Context) {

    var freelanceHoursApiService = FreelanceHoursApi.start()
    var taxDao: TaxDAO

    init {
        val taxDb = TaxDatabase.getDatabase(context)
        taxDao = taxDb!!.taxDao()
    }

    fun getAllTaxes(): LiveData<List<Tax>>? {
        val taxList = taxDao.getAll()

        freelanceHoursApiService.getAllTaxes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TaxAllResponse> {
                    override fun onSuccess(response: TaxAllResponse) {
                        response.taxes?.let {
                            doAsync { taxDao.insertAll(it) }
                        }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) { }
                })

        return taxList
    }

    fun getTax(id: String): Single<TaxSingleResponse> {
        return freelanceHoursApiService.getTax(id)
    }

}