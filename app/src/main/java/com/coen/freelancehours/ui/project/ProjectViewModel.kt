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

    private var repo: ProjectRepository = ProjectRepository()

    var projectList = MutableLiveData<ArrayList<Project>>()
    var status = MutableLiveData<String>()

    init {
        getAllProjects()
    }

    fun setData(projectList: ArrayList<Project>) {
        this.projectList.value = projectList
    }

    fun deleteProject(project: Project) {
        Log.i("TAGZ", "delete project ${project.id}")
        repo.deleteProject(project.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectSingleResponse> {
                    override fun onSuccess(response: ProjectSingleResponse) {
                        status.value = response.status
                    }
                    override fun onError(e: Throwable) { Log.i("TAGZ", e.message) }
                    override fun onSubscribe(d: Disposable) { Log.i("TAGZ", "OnSubscribe!") }
                })
    }

    private fun getAllProjects() {
        repo.getAllProjects()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectAllResponse> {
                    override fun onSuccess(response: ProjectAllResponse) {
                        response.projects?.let {
                            var list = ArrayList<Project>()
                            list.addAll(it)
                            projectList.postValue(list)
                            Log.i("TAGZ", it.toString())
                        }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) { Log.i("TAGZ", "OnSubscribe!") }
                })
    }

}

