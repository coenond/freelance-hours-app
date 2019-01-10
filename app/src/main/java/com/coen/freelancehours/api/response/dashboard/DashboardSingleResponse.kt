package com.coen.freelancehours.api.response.dashboard

import com.coen.freelancehours.model.Dashboard
import com.google.gson.annotations.SerializedName

data class DashboardSingleResponse(
        val status: String?,
        @SerializedName("data") val dashboard: Dashboard,
        val error: String?
)