package com.coen.freelancehours.api.response

import com.coen.freelancehours.model.Project
import com.google.gson.annotations.SerializedName

data class ProjectSingleResponse(
        val status: String?,
        @SerializedName("data") val project: Project,
        val error: String?
)