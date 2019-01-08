package com.coen.freelancehours.api

import com.coen.freelancehours.api.response.tax.TaxAllResponse
import com.coen.freelancehours.api.response.tax.TaxSingleResponse
import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.api.response.project.ProjectAllResponse
import com.coen.freelancehours.api.response.project.ProjectSingleResponse
import io.reactivex.Single
import retrofit2.http.*

interface FreelanceHoursApiService {

    /**
     * Project Endpoints
     */
    @GET("projects/all")
    fun getAllProject(): Single<ProjectAllResponse>

    @GET("projects/get/{id}")
    fun getProject(@Path("id") id: Int): Single<ProjectSingleResponse>

    @POST("projects/store")
    @FormUrlEncoded
    fun storeProject(@Field("user_id") user_id: Int,
                     @Field("name") name: String,
                     @Field("hour_rate") hour_rate: Double)
            : Single<ProjectSingleResponse>


    @GET("projects/{id}/destroy")
    fun deleteProject(@Path("id") id: Int): Single<ProjectSingleResponse>

    /**
     * Hour Endpoints
     */
    @GET("activities/all")
    fun getAllHours(): Single<HourAllResponse>

    @GET("activities/get/{id}")
    fun getHour(@Path("id") id: Int): Single<HourSingleResponse>

    @POST("activities/store")
    @FormUrlEncoded
    fun storeHour(@Field("tax_id") tax_id: Int,
                  @Field("project_id") project_id: Int,
                  @Field("name") name: String,
                  @Field("description") description: String,
                  @Field("started_at") started_at: String,
                  @Field("finished_at") finished_at: String)
            : Single<HourSingleResponse>


    @GET("projects/{id}/destroy")
    fun deleteHour(@Path("id") id: Int): Single<HourSingleResponse>

    /**
     * Tax Endpoints
     */
    @GET("taxes/all")
    fun getAllTaxes(): Single<TaxAllResponse>

    @GET("taxes/get/{id}")
    fun getTax(@Path("id") id: String): Single<TaxSingleResponse>
}