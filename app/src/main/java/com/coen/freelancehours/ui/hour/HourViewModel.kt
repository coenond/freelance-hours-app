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

    private var repo: HourRepository = HourRepository(application.applicationContext)

    var hourList = repo.getAllHours()

//    fun setData(hourList: ArrayList<Hour>) {
//        this.hourList.value = hourList
//    }

}
