package com.coen.freelancehours.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hour(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("description") var description: String,
        @SerializedName("started_at") var started_at: String,
        @SerializedName("finished_at") var finished_at: String,
        @SerializedName("project_id") var project_id: Int,
        @SerializedName("tax_id") var tax_id: Int,
        @SerializedName("duration") var duration: Int,
        @SerializedName("tax_rate") var tax_rate: Double,
        @SerializedName("tax_amount") var tax_amount: Double,
        @SerializedName("cost_excl") var cost_excl: Double,
        @SerializedName("cost_incl") var cost_incl: Double
) : Parcelable