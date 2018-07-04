package com.softwarehut.mvpplayground.domain.introduction

import com.softwarehut.mvp.domain.paramless.BasePresenter
import java.util.*

class IntroductionSlidePresenterImpl(override val view: IntroductionSlideView) :
        BasePresenter(view),
        IntroductionSlidePresenter {

    private val random: Random = Random()

    override fun initialized() {
        super.initialized()
        view.setNumber(random.nextInt())
    }
}