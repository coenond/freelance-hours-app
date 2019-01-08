package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.coen.freelancehours.api.FreelanceHoursApi
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

    var apiService = FreelanceHoursApi.start()
    var taxDao: TaxDAO

    init {
        val taxDb = TaxDatabase.getDatabase(context)
        taxDao = taxDb!!.taxDao()
    }

    fun getAllTaxes(): LiveData<List<Tax>>? {
        val taxList = taxDao.getAll()

        apiService.getAllTaxes()
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

    fun getTax(id: Int): Tax {
        val tax = taxDao.get(id)
        apiService.getTax(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TaxSingleResponse> {
                    override fun onSuccess(response: TaxSingleResponse) {
                        doAsync { taxDao.update(response.tax) }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })

        return tax
    }

    fun storeTax(name: String, rate: Double) {
        apiService.storeTax(name, rate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TaxSingleResponse> {
                    override fun onSuccess(response: TaxSingleResponse) {
                        doAsync { taxDao.insert(response.tax) }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })
    }

    fun deleteTax(tax: Tax) {
        doAsync { taxDao.delete(tax) }
        apiService.deleteTax(tax.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TaxSingleResponse> {
                    override fun onSuccess(response: TaxSingleResponse) {
                        /* TODO: On Success Response */
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) {  }
                })
    }



}