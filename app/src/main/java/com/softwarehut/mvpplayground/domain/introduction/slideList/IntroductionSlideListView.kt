package com.softwarehut.mvpplayground.domain.introduction.slideList

import com.softwarehut.mvp.domain.paramless.View

interface IntroductionSlideListView : View {

    fun initializeSlides()

    fun navigateToLoginSlide()

    fun navigateToInteractionSlide()

    fun navigateToGoSlide()
}