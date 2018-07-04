package com.softwarehut.mvpplayground.domain.form

import android.os.Bundle
import android.view.View
import com.softwarehut.mvp.domain.parametrized.ParamsFragment
import com.softwarehut.mvpplayground.R
import kotlinx.android.synthetic.main.fragment_form.*

class FormFragment : ParamsFragment<FormPresenter>(), FormView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: FormModel = params.getParcelable(MODEL_PARAM_KEY)
        presenter.fillForm(model.name, model.surname)
    }

    override fun getViewResource(): Int {
        return R.layout.fragment_form
    }

    override fun createPresenter(): FormPresenter {
        return FormPresenterImpl(this)
    }

    override fun setName(name: String) {
        this.name.setText(name)
    }

    override fun setSurname(surname: String) {
        this.surname.setText(surname)
    }

    companion object {

        const val MODEL_PARAM_KEY: String = "model"

        fun create(model: FormModel): FormFragment {
            val params = Bundle()
            params.putParcelable(MODEL_PARAM_KEY, model)
            return putParams(FormFragment(), params) as FormFragment
        }
    }
}