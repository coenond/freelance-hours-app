package com.coen.freelancehours.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.project.ProjectAllResponse
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import com.coen.freelancehours.database.project.ProjectDAO
import com.coen.freelancehours.database.project.ProjectDatabase
import com.coen.freelancehours.model.Project
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class ProjectRepository(context: Context) {

    private var apiService = FreelanceHoursApi.start()
    var projectDAO: ProjectDAO

    init {
        val projectDb = ProjectDatabase.getDatabase(context)
        projectDAO = projectDb!!.projectDao()
    }

    fun getAllProjects(): LiveData<List<Project>>? {
        val projectList = projectDAO.getAll()

        apiService.getAllProject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectAllResponse> {
                    override fun onSuccess(response: ProjectAllResponse) {
                        response.projects?.let {
                            doAsync { projectDAO.insertAll(it) }
                        }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) {  }
                })

        return projectList
    }

    fun getProject(id: Int): Project {
        val project = projectDAO.get(id)
        apiService.getProject(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectSingleResponse> {
                    override fun onSuccess(response: ProjectSingleResponse) {
                        doAsync { projectDAO.update(response.project) }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })

        return project
    }

    fun storeProject(user_id: Int, name: String, hourRate: Double) {
        apiService.storeProject(user_id, name, hourRate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectSingleResponse> {
                    override fun onSuccess(response: ProjectSingleResponse) {
                        doAsync { projectDAO.insert(response.project) }
                    }
                    override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                    override fun onSubscribe(d: Disposable) { }
                })
    }

    fun deleteProject(project: Project) {
        doAsync { projectDAO.delete(project) }
        apiService.deleteProject(project.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ProjectSingleResponse> {
                override fun onSuccess(response: ProjectSingleResponse) {
                    /* TODO: On Success Response */
                }
                override fun onError(e: Throwable) { /* TODO: Handle error*/  }
                override fun onSubscribe(d: Disposable) {  }
            })
    }
}