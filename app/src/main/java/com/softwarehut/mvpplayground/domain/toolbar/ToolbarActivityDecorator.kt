package com.softwarehut.mvpplayground.domain.toolbar

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.softwarehut.mvp.domain.decorators.ActivityAccess
import com.softwarehut.mvp.domain.decorators.BaseActivityDecorator

open class ToolbarActivityDecorator(
        val toolbar: Toolbar,
        activity: ActivityAccess
) : BaseActivityDecorator(activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.setSupportActionBar(toolbar)
    }
}