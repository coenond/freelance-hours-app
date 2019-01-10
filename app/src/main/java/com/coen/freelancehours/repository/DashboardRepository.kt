package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.dashboard.DashboardSingleResponse
import com.coen.freelancehours.database.dashboard.DashboardDAO
import com.coen.freelancehours.database.dashboard.DashboardDatabase
import com.coen.freelancehours.model.Dashboard
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class DashboardRepository(context: Context) {

    private var apiService = FreelanceHoursApi.start()
    var dashboardDAO: DashboardDAO

    init {
        val dashboardDb = DashboardDatabase.getDatabase(context)
        dashboardDAO = dashboardDb!!.dashboardDao()
    }

    fun getDashboard(id: Int): LiveData<Dashboard> {
        val data = dashboardDAO.get(id)

        apiService.getDashboard()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<DashboardSingleResponse> {
                    override fun onSuccess(response: DashboardSingleResponse) {
                        doAsync { dashboardDAO.insert(response.dashboard) }
                    }
                    override fun onError(e: Throwable) { Log.i("TAGZ", "error: " + e.message)  }
                    override fun onSubscribe(d: Disposable) {  }
                })
        return data
    }

}