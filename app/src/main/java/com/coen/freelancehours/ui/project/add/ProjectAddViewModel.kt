package com.coen.freelancehours.ui.project.add

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.repository.ProjectRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectAddViewModel(application: Application) : BaseViewModel(application) {

    private var repo: ProjectRepository = ProjectRepository()

    var user_id = MutableLiveData<Int>()
    var name = MutableLiveData<String>()
    var hour_rate = MutableLiveData<Double>()

    var status = MutableLiveData<String>()
    var message = MutableLiveData<String>()

    fun onSubmitClick() {
        val userId = 1
        val name = name.value!!
        val hourRate = hour_rate.value!!

        repo.storeProject(userId, name, hourRate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectSingleResponse> {
                    override fun onSuccess(response: ProjectSingleResponse) {
                        status.value = response.status
                        message.value = "Project $name added."
                    }
                    override fun onError(e: Throwable) { message.value = e.message }
                    override fun onSubscribe(d: Disposable) { Log.i("TAGZ", "OnSubscribe!") }
                })
    }

    fun getNameAttribute(): MutableLiveData<String> {
        return name
    }

    fun getHourRateAttribute(): MutableLiveData<Double> {
        return hour_rate
    }
}

