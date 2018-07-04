package com.softwarehut.mvpplayground.domain.form

import com.softwarehut.mvp.domain.paramless.BasePresenter

class FormPresenterImpl(override val view: FormView) :
        BasePresenter(view),
        FormPresenter {

    override fun fillForm(name: String, surname: String) {
        view.setName(name)
        view.setSurname(surname)
    }
}