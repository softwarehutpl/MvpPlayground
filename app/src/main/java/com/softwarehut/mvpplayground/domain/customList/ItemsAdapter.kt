package com.softwarehut.mvpplayground.domain.customList

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.softwarehut.mvpplayground.R

class ItemsAdapter : ListAdapter<CustomListItem, CustomListFragment.ViewHolder>(ItemsDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomListFragment.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CustomListFragment.ViewHolder(layoutInflater.inflate(R.layout.view_custom_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomListFragment.ViewHolder, position: Int) {
    }
}