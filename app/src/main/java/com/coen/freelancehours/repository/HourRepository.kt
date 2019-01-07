package com.coen.freelancehours.repository

import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.api.response.hour.HourAllResponse
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.model.Tax
import io.reactivex.Single

class HourRepository {

    var freelanceHoursApiService = FreelanceHoursApi.start()

    fun getAllHours(): Single<HourAllResponse> {
        return freelanceHoursApiService.getAllHours()
    }

    fun getHour(id: String): Single<HourSingleResponse> {
        return freelanceHoursApiService.getHour(id)
    }

    fun storeHour(
            tax: Tax,
            project: Project,
            name: String,
            description: String,
            started_at: String,
            finished_at: String
            ): Single<HourSingleResponse> {
        return freelanceHoursApiService.storeHour(
                tax.id, project.id, name, description,
                started_at, finished_at)
    }

    fun deleteHour(id: Int): Single<HourSingleResponse> {
        return freelanceHoursApiService.deleteHour(id)
    }

}