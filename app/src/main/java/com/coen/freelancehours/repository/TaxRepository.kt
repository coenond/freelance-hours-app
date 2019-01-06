package com.coen.freelancehours.repository

import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.response.tax.TaxAllResponse
import com.coen.freelancehours.api.response.tax.TaxSingleResponse
import io.reactivex.Single

class TaxRepository {

    var freelanceHoursApiService = FreelanceHoursApi.start()

    fun getAllTaxes(): Single<TaxAllResponse> {
        return freelanceHoursApiService.getAllTaxes()
    }

    fun getTax(id: String): Single<TaxSingleResponse> {
        return freelanceHoursApiService.getTax(id)
    }

}