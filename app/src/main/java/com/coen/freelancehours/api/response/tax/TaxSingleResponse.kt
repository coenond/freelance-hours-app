package com.coen.freelancehours.api.response.tax

import com.coen.freelancehours.model.Tax
import com.google.gson.annotations.SerializedName

data class TaxSingleResponse(
        val status: String?,
        @SerializedName("data") val tax: Tax,
        val error: String?
)