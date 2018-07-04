package com.softwarehut.mvpplayground.domain.introduction.slideList

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.softwarehut.mvp.domain.paramless.BaseActivity
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.errors.UnknownNavigationException
import com.softwarehut.mvpplayground.domain.introduction.IntroductionSlideFragment
import com.softwarehut.mvpplayground.domain.introduction.OnSlidesNavigationListener
import com.softwarehut.mvpplayground.domain.introduction.Slide
import kotlinx.android.synthetic.main.activity_introduction.*

class IntroductionSlideListActivity : BaseActivity<IntroductionSlideListPresenter>(),
        IntroductionSlideListView {

    lateinit var slides: Slides

    override fun initializeSlides() {
        slides = createSlides()
        viewPager.adapter = IntroductionSlidesAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                val slide = slides[position]
                navigation.selectedItemId = slide.menuId
            }
        })
        setOnSlidesNavigationListener(object : OnSlidesNavigationListener {
            override fun onLoginNavigation() {
                navigateToLoginSlide()
            }

            override fun onInteractionNavigation() {
                navigateToInteractionSlide()
            }

            override fun onGoNavigation() {
                navigateToGoSlide()
            }
        })
    }

    private fun setOnSlidesNavigationListener(listener: OnSlidesNavigationListener) {
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_login -> listener.onLoginNavigation()
                R.id.navigation_interaction -> listener.onInteractionNavigation()
                R.id.navigation_go -> listener.onGoNavigation()
                else -> throw UnknownNavigationException()
            }
            true
        }
    }

    private fun createSlides(): Slides {
        return Slides(arrayListOf(Slide(R.id.navigation_login),
                Slide(R.id.navigation_interaction),
                Slide(R.id.navigation_go)))
    }

    override fun navigateToLoginSlide() {
        viewPager.currentItem = 0
    }

    override fun navigateToInteractionSlide() {
        viewPager.currentItem = 1
    }

    override fun navigateToGoSlide() {
        viewPager.currentItem = 2
    }

    override fun createPresenter(): IntroductionSlideListPresenter {
        return IntroductionSlideListPresenterImpl(this)
    }

    override fun getContentView(): Int {
        return R.layout.activity_introduction
    }

    companion object {
        fun navigate(activity: Activity) {
            val intent = Intent(activity.baseContext, IntroductionSlideListActivity::class.java)
            activity.startActivity(intent)
        }
    }

    class IntroductionSlidesAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = IntroductionSlideFragment()

        override fun getCount(): Int {
            return 3
        }
    }
}