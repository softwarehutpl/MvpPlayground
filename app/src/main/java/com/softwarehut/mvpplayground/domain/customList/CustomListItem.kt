package com.softwarehut.mvpplayground.domain.customList

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity
class CustomListItem()
    : Parcelable {

    @Ignore
    private val undefinedId: Long = -1

    @PrimaryKey
    @SerializedName("id")
    var id: Long = undefinedId

    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(parcel: Parcel) : this(parcel.readLong())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomListItem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomListItem> {

        override fun createFromParcel(parcel: Parcel): CustomListItem {
            return CustomListItem(parcel)
        }

        override fun newArray(size: Int): Array<CustomListItem?> {
            return arrayOfNulls(size)
        }
    }
}