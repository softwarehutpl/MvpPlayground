package com.softwarehut.mvpplayground.domain.form

import com.softwarehut.mvp.domain.paramless.View

interface FormView : View {

    fun setName(name: String)

    fun setSurname(surname: String)
}