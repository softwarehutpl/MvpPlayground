package com.softwarehut.mvpplayground.domain.main

import com.softwarehut.mvp.domain.paramless.View
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import com.softwarehut.mvpplayground.domain.form.FormModel

interface MainView : View {

    fun initScreens(model: FormModel, customListModel: CustomListModel)

    fun navigateToHome()

    fun navigateToForm()

    fun navigateToCustomList()

    fun navigateToIntroductionLogin()

    fun navigateTo(screenIndex: Int)

    fun onScreenChanged(listener: (Int) -> Unit)

    fun navigateToStack()
}