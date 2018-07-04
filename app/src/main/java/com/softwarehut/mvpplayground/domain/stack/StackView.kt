package com.softwarehut.mvpplayground.domain.stack

import com.softwarehut.mvp.domain.paramless.View
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import java.util.*

interface StackView : View {

    val items: Stack<CustomListModel>

    fun pushToStack(customListModel: CustomListModel)

    fun popFromStack()
}