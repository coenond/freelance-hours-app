package com.coen.freelancehours.ui.hour.add

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.model.Tax
import com.coen.freelancehours.repository.HourRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HourAddViewModel(application: Application) : BaseViewModel(application) {

    private var repo: HourRepository = HourRepository(application.applicationContext)

    var tax = MutableLiveData<Tax>()
    var project = MutableLiveData<Project>()
    var name = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var started_at = MutableLiveData<String>()
    var finished_at = MutableLiveData<String>()

    var status = MutableLiveData<String>()
    var message = MutableLiveData<String>()

    fun onSubmitClick() {
        repo.storeHour(
                    tax.value!!,
                    project.value!!,
                    name.value!!,
                    description.value!!,
                    started_at.value!!,
                    finished_at.value!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourSingleResponse> {
                    override fun onSuccess(response: HourSingleResponse) {
                        Log.i("TAGZ", "On success !!")
                        status.value = response.status
                        message.value = "Hour ${name.value!!} added."
                    }
                    override fun onError(e: Throwable) { Log.i("TAGZ", "Error: ${e.message}") }
                    override fun onSubscribe(d: Disposable) { Log.i("TAGZ", "OnSubscribe!") }
                })
    }

    fun getTaxAttribute(): MutableLiveData<Tax> { return tax }
    fun getProjectAttribute(): MutableLiveData<Project> { return project }
    fun getNameAttribute(): MutableLiveData<String> { return name }
    fun getDescriptionAttribute(): MutableLiveData<String> { return description }
    fun getStartedAtAttribute(): MutableLiveData<String> { return started_at }
    fun getFinishedAtAttribute(): MutableLiveData<String> { return finished_at }
}

