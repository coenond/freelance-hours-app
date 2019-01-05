package com.coen.freelancehours.repository

import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.FreelanceHoursApiService
import com.coen.freelancehours.api.response.ProjectAllResponse
import com.coen.freelancehours.api.response.ProjectSingleResponse
import io.reactivex.Single

class ProjectRepository {

    var freelanceHoursApiService = FreelanceHoursApi.start()

    fun getAllProjects(): Single<ProjectAllResponse> {
        return freelanceHoursApiService.getAllProject()
    }

    fun getProject(id: String): Single<ProjectSingleResponse> {
        return freelanceHoursApiService.getProject(id)
    }

}