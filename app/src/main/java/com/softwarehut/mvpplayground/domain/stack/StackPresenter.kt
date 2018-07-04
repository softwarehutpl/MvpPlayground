package com.softwarehut.mvpplayground.domain.stack

import com.softwarehut.mvp.domain.paramless.Presenter

interface StackPresenter : Presenter {

    fun onPushToStackButtonClick()

    fun addItemsClick()
}