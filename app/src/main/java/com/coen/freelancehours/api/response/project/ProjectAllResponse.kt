package com.coen.freelancehours.api.response.project

import com.coen.freelancehours.model.Project
import com.google.gson.annotations.SerializedName

data class ProjectAllResponse(
        val status: String?,
        @SerializedName("data") val projects: List<Project>?,
        val error: String?
)