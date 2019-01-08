package com.coen.freelancehours.ui.hour.add

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.model.Tax
import com.coen.freelancehours.repository.HourRepository
import com.coen.freelancehours.repository.ProjectRepository
import com.coen.freelancehours.repository.TaxRepository

class HourAddViewModel(application: Application) : BaseViewModel(application) {

    private var hourRepository: HourRepository = HourRepository(application.applicationContext)
    private var projectRepository: ProjectRepository = ProjectRepository(application.applicationContext)
    private var taxRepository: TaxRepository = TaxRepository(application.applicationContext)

    var tax = MutableLiveData<Tax>()
    var project = MutableLiveData<Project>()
    var name = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var started_at = MutableLiveData<String>()
    var finished_at = MutableLiveData<String>()

    var projectList = projectRepository.getAllProjects()
    var taxList = taxRepository.getAllTaxes()

    var status = MutableLiveData<String>()
    var message = MutableLiveData<String>()

    fun onSubmitClick() {
        hourRepository.storeHour(tax.value!!, project.value!!, name.value!!,
                description.value!!, started_at.value!!, finished_at.value!!)

        /* TODO: make this dynamic with the response */
        status.value = "success"
    }
}

