package com.coen.freelancehours.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Project(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("hour_rate") var hourRate: String,
        @SerializedName("user_id") var user_id: Int
) : Parcelable