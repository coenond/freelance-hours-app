package com.coen.freelancehours.api

import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.api.response.project.ProjectAllResponse
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FreelanceHoursApiService {

    /**
     * Project Endpoints
     */
    @GET("projects/all")
    fun getAllProject(): Single<ProjectAllResponse>

    @GET("projects/get/{id}")
    fun getProject(@Path("id") id: String): Single<ProjectSingleResponse>

    /**
     * Hour Endpoints
     */
    @GET("activities/all")
    fun getAllHours(): Single<HourAllResponse>

    @GET("activities/get/{id}")
    fun getHour(@Path("id") id: String): Single<HourSingleResponse>
}