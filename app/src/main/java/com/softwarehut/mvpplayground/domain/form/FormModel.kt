package com.softwarehut.mvpplayground.domain.form

import android.os.Parcel
import android.os.Parcelable

open class FormModel(var name: String,
                     var surname: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FormModel> {
        override fun createFromParcel(parcel: Parcel): FormModel {
            return FormModel(parcel)
        }

        override fun newArray(size: Int): Array<FormModel?> {
            return arrayOfNulls(size)
        }
    }
}