package com.example.jim.data.model

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
    }


    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }


        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}


