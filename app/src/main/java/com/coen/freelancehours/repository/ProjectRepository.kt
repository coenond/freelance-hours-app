package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.project.ProjectAllResponse
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import com.coen.freelancehours.database.project.ProjectDAO
import com.coen.freelancehours.database.project.ProjectDatabase
import com.coen.freelancehours.model.Project
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class ProjectRepository(context: Context) {

    var freelanceHoursApiService = FreelanceHoursApi.start()
    var projectDAO: ProjectDAO

    init {
        val projectDb = ProjectDatabase.getDatabase(context)
        projectDAO = projectDb!!.projectDao()
    }

    fun getAllProjects(): LiveData<List<Project>>? {
        val projectList = projectDAO.getAll()

        freelanceHoursApiService.getAllProject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectAllResponse> {
                    override fun onSuccess(response: ProjectAllResponse) {
                        response.projects?.let {
                            doAsync { projectDAO.insertAll(it) }
                        }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) {  }
                })

        return projectList
    }

    fun getProject(id: Int): Single<ProjectSingleResponse> {
        return freelanceHoursApiService.getProject(id)
    }

    fun storeProject(user_id: Int, name: String, hourRate: Double) {
        freelanceHoursApiService.storeProject(user_id, name, hourRate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectSingleResponse> {
                    override fun onSuccess(response: ProjectSingleResponse) {
                        doAsync { projectDAO.insert(response.project) }
                    }
                    override fun onError(e: Throwable) {  }
                    override fun onSubscribe(d: Disposable) { }
                })
    }

    fun deleteProject(id: Int): Single<ProjectSingleResponse> {
        return freelanceHoursApiService.deleteProject(id)
    }


}