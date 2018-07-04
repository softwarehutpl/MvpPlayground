package com.softwarehut.mvpplayground.domain.splash

import com.softwarehut.mvp.domain.paramless.BasePresenter

class SplashPresenterImpl(override val view: SplashView) :
        BasePresenter(view),
        SplashPresenter {

    override fun onButtonClick() {
        view.navigateToIntroductionScreen()
    }
}