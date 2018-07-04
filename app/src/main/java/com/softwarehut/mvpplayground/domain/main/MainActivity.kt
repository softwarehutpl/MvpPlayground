package com.softwarehut.mvpplayground.domain.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.softwarehut.mvp.domain.decorators.BaseActivityDecorator
import com.softwarehut.mvp.domain.parametrized.ParamsActivity
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import com.softwarehut.mvpplayground.domain.form.FormModel
import com.softwarehut.mvpplayground.domain.introductionLogin.IntroductionLoginActivity
import com.softwarehut.mvpplayground.domain.navigationDrawer.NavigationDrawerActivityDecorator
import com.softwarehut.mvpplayground.domain.stack.StackActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_navigation_drawer.*
import kotlinx.android.synthetic.main.content_navigation_drawer.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class MainActivity : ParamsActivity<MainPresenter>(), MainView {

    private val kodein by closestKodein()

    private var currentFragmentIndex: Int = INITIAL_FRAGMENT_INDEX

    override fun createPresenter(): MainPresenter {
        val bindingContainer = kodein
        val newInstance = bindingContainer.newInstance { MainPresenterImpl(this@MainActivity, instance()) }
        val presenter by newInstance
        return presenter
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun createDecorator(): BaseActivityDecorator {
        return NavigationDrawerActivityDecorator(drawerLayout, navView, presenter, toolbar, this)
    }

    override fun initScreens(model: FormModel, customListModel: CustomListModel) {
        viewPager.adapter = MainPagerAdapter(supportFragmentManager, model, customListModel)
    }

    override fun onScreenChanged(listener: (Int) -> Unit) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                listener.invoke(position)
            }
        })
    }

    override fun navigateToHome() {
        navigateTo(INITIAL_FRAGMENT_INDEX)
    }

    override fun navigateToForm() {
        navigateTo(1)
    }

    override fun navigateToIntroductionLogin() {
        IntroductionLoginActivity.navigate(this)
    }

    override fun navigateToCustomList() {
        navigateTo(2)
    }

    override fun navigateTo(screenIndex: Int) {
        viewPager.currentItem = screenIndex
    }

    override fun navigateToStack() {
        StackActivity.navigate(this, Bundle())
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(CURRENT_FRAGMENT_INDEX_STORAGE_KEY, currentFragmentIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        currentFragmentIndex = savedInstanceState!!.getInt(CURRENT_FRAGMENT_INDEX_STORAGE_KEY)
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {

        private const val INITIAL_FRAGMENT_INDEX = 0

        private const val CURRENT_FRAGMENT_INDEX_STORAGE_KEY = "currentFragmentIndex"

        fun navigate(activity: Activity, params: Bundle) {
            val intent = Intent(activity.baseContext, MainActivity::class.java)
            putParams(intent, params)
            activity.startActivity(intent)
        }

        fun navigate(fragment: Fragment, params: Bundle) {
            val intent = Intent(fragment.activity, MainActivity::class.java)
            putParams(intent, params)
            fragment.startActivity(intent)
        }
    }
}