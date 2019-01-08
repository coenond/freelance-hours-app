package com.coen.freelancehours.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "taxes")
data class Tax(
        @SerializedName("id")  @PrimaryKey var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("rate") var rate: Double
) : Parcelable {

    override fun toString(): String {
        return "$name - $rate%"
    }
}