package com.softwarehut.mvpplayground.domain.navigationDrawer

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.softwarehut.mvp.domain.decorators.ActivityAccess
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.toolbar.ToolbarActivityDecorator

class NavigationDrawerActivityDecorator(
        val drawerLayout: DrawerLayout,
        val navigationView: NavigationView,
        val presenter: NavigationDrawerPresenter,
        toolbar: Toolbar,
        activity: ActivityAccess
) : ToolbarActivityDecorator(toolbar, activity), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = activity as Activity
        val toggle = ActionBarDrawerToggle(
                activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun shouldHandleBackPressed(): Boolean {
        return !drawerLayout.isDrawerOpen(GravityCompat.START)
    }

    override fun onNotHandleBackPressed() {
        super.onNotHandleBackPressed()
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                presenter.onNavigateToHome()
            }
            R.id.nav_form -> {
                presenter.onNavigateToForm()
            }
            R.id.nav_custom_list -> {
                presenter.onNavigateToCustomList()
            }
            R.id.nav_introduction_login -> {
                presenter.onNavigateToIntroductionLogin()
            }
            R.id.nav_stack -> {
                presenter.onNavigateToStack()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}