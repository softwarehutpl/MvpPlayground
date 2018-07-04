package com.softwarehut.mvpplayground.domain.home

import com.softwarehut.mvp.domain.paramless.BaseFragment
import com.softwarehut.mvpplayground.R

class HomeFragment : BaseFragment<HomePresenter>(), HomeView {
    override fun getViewResource(): Int {
        return R.layout.fragment_home
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenterImpl(this)
    }

    companion object {

        fun create(): HomeFragment {
            return HomeFragment()
        }
    }
}