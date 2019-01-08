package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.api.response.project.ProjectAllResponse
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

    private var freelanceHoursApiService = FreelanceHoursApi.start()
    var hourDao: HourDAO

    init {
        val hourDb = HourDatabase.getDatabase(context)
        hourDao = hourDb!!.hourDao()
    }

    fun getAllHours(): LiveData<List<Hour>>? {
        val hourList = hourDao.getAll()

        freelanceHoursApiService.getAllHours()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourAllResponse> {
                    override fun onSuccess(response: HourAllResponse) {
                        response.hours?.let {
                            doAsync { hourDao.insertAll(it) }
                        }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) { }
                })

        return hourList
    }

    fun getHour(id: String): Single<HourSingleResponse> {
        return freelanceHoursApiService.getHour(id)
    }

    fun storeHour(
            tax: Tax,
            project: Project,
            name: String,
            description: String,
            started_at: String,
            finished_at: String
            ): Single<HourSingleResponse> {
        return freelanceHoursApiService.storeHour(
                tax.id, project.id, name, description,
                started_at, finished_at)
    }

    fun deleteHour(id: Int): Single<HourSingleResponse> {
        return freelanceHoursApiService.deleteHour(id)
    }

}