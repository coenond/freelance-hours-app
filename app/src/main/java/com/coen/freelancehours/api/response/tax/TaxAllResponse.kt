package com.coen.freelancehours.api.response.tax

import com.coen.freelancehours.model.Tax
import com.google.gson.annotations.SerializedName

data class TaxAllResponse(
        val status: String?,
        @SerializedName("data") val taxes: List<Tax>?,
        val error: String?
)