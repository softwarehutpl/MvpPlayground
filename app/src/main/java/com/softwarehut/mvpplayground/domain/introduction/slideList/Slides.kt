package com.softwarehut.mvpplayground.domain.introduction.slideList

import android.support.annotation.IdRes
import com.softwarehut.mvpplayground.domain.introduction.Slide
import com.softwarehut.mvpplayground.domain.introduction.SlideNotFoundException

class Slides(collection: Collection<Slide>) : ArrayList<Slide>(collection) {

    fun getById(@IdRes id: Int): Slide {
        for (slide in this) {
            if (slide.menuId == id) {
                return slide
            }
        }
        throw SlideNotFoundException()
    }
}