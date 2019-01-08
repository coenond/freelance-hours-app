package com.coen.freelancehours.ui.project

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.coen.freelancehours.api.response.project.ProjectAllResponse
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import com.coen.freelancehours.base.BaseViewModel
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.repository.ProjectRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectViewModel(application: Application) : BaseViewModel(application) {

    private var repo: ProjectRepository = ProjectRepository(application.applicationContext)

    var projectList = repo.getAllProjects()
    var status = MutableLiveData<String>()

    fun deleteProject(project: Project) = repo.deleteProject(project)

}

