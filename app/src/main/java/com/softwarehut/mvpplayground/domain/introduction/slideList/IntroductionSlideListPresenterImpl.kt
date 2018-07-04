package com.softwarehut.mvpplayground.domain.introduction.slideList

import com.softwarehut.mvp.domain.paramless.BasePresenter


class IntroductionSlideListPresenterImpl(override val view: IntroductionSlideListView) :
        BasePresenter(view),
        IntroductionSlideListPresenter {

    override fun initialized() {
        super.initialized()
        view.initializeSlides()
    }
}