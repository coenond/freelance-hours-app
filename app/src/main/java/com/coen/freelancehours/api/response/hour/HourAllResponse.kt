package com.coen.freelancehours.api.response.hour

import com.coen.freelancehours.model.Hour
import com.google.gson.annotations.SerializedName

data class HourAllResponse(
        val status: String?,
        @SerializedName("data") val hours: List<Hour>?,
        val error: String?
)