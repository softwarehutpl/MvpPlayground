package com.softwarehut.mvpplayground.domain.introduction

import android.os.Bundle
import android.view.View
import com.softwarehut.mvp.domain.paramless.BaseFragment
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.main.MainActivity
import kotlinx.android.synthetic.main.fragment_introduction.*

class IntroductionSlideFragment : BaseFragment<IntroductionSlidePresenter>(), IntroductionSlideView {

    override fun setNumber(number: Int) {
        this.number.text = number.toString()
    }

    override fun getViewResource(): Int {
        return R.layout.fragment_introduction
    }

    override fun createPresenter(): IntroductionSlidePresenter {
        return IntroductionSlidePresenterImpl(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            navigateToMainScreen(Bundle())
        }
    }

    override fun navigateToMainScreen(params: Bundle) {
        MainActivity.navigate(this, params)
    }
}