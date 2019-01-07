package com.coen.freelancehours.repository

import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.project.ProjectAllResponse
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import io.reactivex.Single

class ProjectRepository {

    var freelanceHoursApiService = FreelanceHoursApi.start()

    fun getAllProjects(): Single<ProjectAllResponse> {
        return freelanceHoursApiService.getAllProject()
    }

    fun getProject(id: Int): Single<ProjectSingleResponse> {
        return freelanceHoursApiService.getProject(id)
    }

    fun storeProject(user_id: Int, name: String, hour_rate: Double): Single<ProjectSingleResponse> {
        return freelanceHoursApiService.storeProject(user_id, name, hour_rate)
    }

}