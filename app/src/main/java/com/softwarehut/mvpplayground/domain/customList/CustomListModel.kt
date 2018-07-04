package com.softwarehut.mvpplayground.domain.customList

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
open class CustomListModel() : Parcelable {

    @Ignore
    private val undefinedId: Long = -1

    @PrimaryKey
    var id: Long = undefinedId

    var items: ArrayList<CustomListItem> = ArrayList()

    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(id: Long, items: ArrayList<CustomListItem>) : this(id) {
        this.items = items
    }

    constructor(parcel: Parcel) : this(parcel.readLong(), parcel.createTypedArrayList(CustomListItem.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomListModel> {
        override fun createFromParcel(parcel: Parcel): CustomListModel {
            return CustomListModel(parcel)
        }

        override fun newArray(size: Int): Array<CustomListModel?> {
            return arrayOfNulls(size)
        }
    }
}