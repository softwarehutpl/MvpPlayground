package com.softwarehut.mvpplayground.domain.customList

import android.support.v7.util.DiffUtil

class ItemsDiff : DiffUtil.ItemCallback<CustomListItem>() {

    override fun areItemsTheSame(oldItem: CustomListItem?, newItem: CustomListItem?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: CustomListItem?, newItem: CustomListItem?): Boolean {
        return newItem?.equals(oldItem)!!
    }
}