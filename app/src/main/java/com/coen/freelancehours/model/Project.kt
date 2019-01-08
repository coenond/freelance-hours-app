package com.coen.freelancehours.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "projects")
data class Project(
        @SerializedName("id") @PrimaryKey var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("hour_rate") var hourRate: Double,
        @SerializedName("user_id") var user_id: Int,
        @SerializedName("logs") var logs: Int,
        @SerializedName("revenue") var revenue: Int,
        @SerializedName("hours") var hours: Int
) : Parcelable {

    override fun toString(): String {
        return "$name - $hourRate per hour"
    }
}