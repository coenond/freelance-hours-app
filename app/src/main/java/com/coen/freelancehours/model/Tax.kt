package com.coen.freelancehours.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tax(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("rate") var rate: Double
) : Parcelable