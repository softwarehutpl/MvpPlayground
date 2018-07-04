package com.softwarehut.mvpplayground.domain.home

import com.softwarehut.mvp.domain.paramless.BasePresenter

class HomePresenterImpl(override val view: HomeView) : BasePresenter(view), HomePresenter