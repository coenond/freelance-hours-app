package com.coen.freelancehours.api.response.hour

import com.coen.freelancehours.model.Hour
import com.google.gson.annotations.SerializedName

data class HourSingleResponse(
        val status: String?,
        @SerializedName("data") val hour: Hour,
        val error: String?
)