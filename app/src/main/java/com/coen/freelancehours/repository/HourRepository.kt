package com.coen.freelancehours.repository

import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.hour.HourSingleResponse
import com.coen.freelancehours.api.response.hour.HourAllResponse
import io.reactivex.Single

class HourRepository {

    var freelanceHoursApiService = FreelanceHoursApi.start()

    fun getAllHours(): Single<HourAllResponse> {
        return freelanceHoursApiService.getAllHours()
    }

    fun getHour(id: String): Single<HourSingleResponse> {
        return freelanceHoursApiService.getHour(id)
    }

}