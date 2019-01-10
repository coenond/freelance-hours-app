package com.coen.freelancehours.ui.dashboard

import android.app.Application
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.repository.DashboardRepository

class DashboardViewModel(application: Application) : BaseViewModel(application) {

    private var repo: DashboardRepository = DashboardRepository(application.applicationContext)

    var dashboardData = repo.getDashboard(1)
}
