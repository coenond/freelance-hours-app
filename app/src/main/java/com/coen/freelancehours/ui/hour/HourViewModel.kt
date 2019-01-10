package com.coen.freelancehours.ui.hour

import android.app.Application
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.model.Hour
import com.coen.freelancehours.repository.HourRepository

class HourViewModel(application: Application) : BaseViewModel(application) {

    private var repo: HourRepository = HourRepository(application.applicationContext)

    var hourList = repo.getAllHours()

    fun deleteHour(hour: Hour) = repo.deleteHour(hour)

}
