package com.softwarehut.mvpplayground.domain.splash

import android.os.Bundle
import com.softwarehut.mvp.domain.paramless.BaseActivity
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.introduction.slideList.IntroductionSlideListActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.newInstance

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView, KodeinAware {

    override val kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button.setOnClickListener {
            presenter.onButtonClick()
        }
    }

    override fun navigateToIntroductionScreen() {
        IntroductionSlideListActivity.navigate(this)
    }

    override fun createPresenter(): SplashPresenter {
        val bindingContainer = kodein
        val newInstance = bindingContainer.newInstance { SplashPresenterImpl(this@SplashActivity) }
        val presenter by newInstance
        return presenter
    }

    override fun getContentView(): Int {
        return R.layout.activity_splash
    }
}