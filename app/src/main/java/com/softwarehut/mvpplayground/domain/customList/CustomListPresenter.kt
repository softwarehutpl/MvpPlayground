package com.softwarehut.mvpplayground.domain.customList

import com.softwarehut.mvp.domain.paramless.Presenter

interface CustomListPresenter : Presenter {

    fun onAddClick()

    fun requestItems(initialModel: CustomListModel)
}