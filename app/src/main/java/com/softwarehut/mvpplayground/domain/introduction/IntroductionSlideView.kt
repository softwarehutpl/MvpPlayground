package com.softwarehut.mvpplayground.domain.introduction

import android.os.Bundle
import com.softwarehut.mvp.domain.paramless.View

interface IntroductionSlideView : View {

    fun setNumber(number: Int)

    fun navigateToMainScreen(params: Bundle)
}