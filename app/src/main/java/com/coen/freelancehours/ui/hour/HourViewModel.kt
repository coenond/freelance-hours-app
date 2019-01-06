package com.coen.freelancehours.ui.hour

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.model.Hour
import com.coen.freelancehours.repository.HourRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HourViewModel(application: Application) : BaseViewModel(application) {

    private var repo: HourRepository = HourRepository()

    var hourList = MutableLiveData<ArrayList<Hour>>()

    init {
        getAllHours()
    }

    fun setData(hourList: ArrayList<Hour>) {
        this.hourList.value = hourList
    }

    private fun getAllHours() {
        repo.getAllHours()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<HourAllResponse> {
                    override fun onSuccess(response: HourAllResponse) {
                        response.hours?.let {
                            var list = ArrayList<Hour>()
                            list.addAll(it)
                            hourList.postValue(list)
                        }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) { Log.i("TAGZ", "OnSubscribe!") }
                })
    }

}
