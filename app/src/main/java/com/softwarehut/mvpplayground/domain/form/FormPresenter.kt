package com.softwarehut.mvpplayground.domain.form

import com.softwarehut.mvp.domain.paramless.Presenter

interface FormPresenter : Presenter {
    fun fillForm(name: String, surname: String)
}