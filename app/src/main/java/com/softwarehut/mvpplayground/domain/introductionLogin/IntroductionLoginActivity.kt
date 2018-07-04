package com.softwarehut.mvpplayground.domain.introductionLogin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.softwarehut.mvp.domain.paramless.BaseActivity
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.introduction.IntroductionSlidePresenter
import com.softwarehut.mvpplayground.domain.introduction.IntroductionSlidePresenterImpl
import com.softwarehut.mvpplayground.domain.introduction.IntroductionSlideView
import com.softwarehut.mvpplayground.domain.main.MainActivity
import kotlinx.android.synthetic.main.fragment_introduction.*

class IntroductionLoginActivity : BaseActivity<IntroductionSlidePresenter>(), IntroductionSlideView {

    override fun setNumber(number: Int) {
        this.number.text = number.toString()
    }

    override fun getContentView(): Int {
        return R.layout.fragment_introduction
    }

    override fun createPresenter(): IntroductionSlidePresenter {
        return IntroductionSlidePresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button.setOnClickListener {
            navigateToMainScreen(Bundle())
        }
    }

    override fun navigateToMainScreen(params: Bundle) {
        MainActivity.navigate(this, params)
    }

    companion object {

        fun navigate(activity: Activity) {
            val intent = Intent(activity.baseContext, IntroductionLoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}