package com.coen.freelancehours.ui.project.add

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.repository.ProjectRepository

class ProjectAddViewModel(application: Application) : BaseViewModel(application) {

    private var repo: ProjectRepository = ProjectRepository(application.applicationContext)

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

        /* TODO: make this dynamic with the response */
        status.value = "success"
    }
}

