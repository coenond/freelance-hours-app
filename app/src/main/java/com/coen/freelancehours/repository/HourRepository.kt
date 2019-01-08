package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.database.hour.HourDAO
import com.coen.freelancehours.database.hour.HourDatabase
import com.coen.freelancehours.model.Hour
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.model.Tax
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class HourRepository(context: Context) {

    private var apiService = FreelanceHoursApi.start()
    var hourDao: HourDAO

    init {
        val hourDb = HourDatabase.getDatabase(context)
        hourDao = hourDb!!.hourDao()
    }

    fun getAllHours(): LiveData<List<Hour>>? {
        val hourList = hourDao.getAll()

        apiService.getAllHours()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourAllResponse> {
                    override fun onSuccess(response: HourAllResponse) {
                        response.hours?.let {
                            doAsync { hourDao.insertAll(it) }
                        }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })

        return hourList
    }

    fun getHour(id: Int): Hour {
        val hour = hourDao.get(id)
        apiService.getHour(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourSingleResponse> {
                    override fun onSuccess(response: HourSingleResponse) {
                        doAsync { hourDao.update(response.hour) }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })

        return hour
    }

    fun storeHour(  tax: Tax,
                    project: Project,
                    name: String,
                    description: String,
                    started_at: String,
                    finished_at: String ) {
        apiService.storeHour(tax.id, project.id, name, description, started_at, finished_at)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourSingleResponse> {
                    override fun onSuccess(response: HourSingleResponse) {
                        doAsync { hourDao.insert(response.hour) }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })
    }

    fun deleteHour(hour: Hour) {
        doAsync {  hourDao.delete(hour) }
        apiService.deleteHour(hour.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourSingleResponse> {
                    override fun onSuccess(response: HourSingleResponse) {
                        /* TODO: On Success Response */
                    }
                    override fun onError(e: Throwable) {  /* TODO: Handle error*/ }
                    override fun onSubscribe(d: Disposable) {  }
                })
    }

}