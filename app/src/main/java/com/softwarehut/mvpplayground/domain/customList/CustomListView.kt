package com.softwarehut.mvpplayground.domain.customList

import com.softwarehut.mvp.domain.paramless.View

interface CustomListView : View {

    fun setListItems(items: List<CustomListItem>)
}