package com.coen.freelancehours.api

import com.coen.freelancehours.api.response.ProjectAllResponse
import com.coen.freelancehours.api.response.ProjectSingleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FreelanceHoursApiService {

    /**
     * Project Endpoints
     */
    @GET("projects/all")
    fun getAllProject(): Single<ProjectAllResponse>

    @GET("projects/get/{id}")
    fun getProject(@Path("id") id: String): Single<ProjectSingleResponse>
}