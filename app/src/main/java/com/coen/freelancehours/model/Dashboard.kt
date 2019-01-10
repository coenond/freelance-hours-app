package com.coen.freelancehours.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "dashboards")
data class Dashboard(
        @SerializedName("id") @PrimaryKey var id: Int,
        @SerializedName("total_revenue") var total_revenue: Int,
        @SerializedName("total_projects") var total_projects: Int,
        @SerializedName("total_hours") var total_hours: Int,
        @SerializedName("total_logs") var total_logs: Int
) : Parcelable