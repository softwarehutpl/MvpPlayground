package com.softwarehut.mvpplayground.domain.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import com.softwarehut.mvpplayground.domain.customList.CustomListFragment
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import com.softwarehut.mvpplayground.domain.errors.UnknownFragmentException
import com.softwarehut.mvpplayground.domain.form.FormFragment
import com.softwarehut.mvpplayground.domain.form.FormModel
import com.softwarehut.mvpplayground.domain.home.HomeFragment

class MainPagerAdapter(fragmentManager: FragmentManager,
                       private val formModel: FormModel,
                       private val customListModel: CustomListModel) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentsCache: Array<Fragment?> = arrayOfNulls(count)

    override fun getItem(position: Int): Fragment {
        val cachedFragment = fragmentsCache[position]
        if (cachedFragment != null) {
            return cachedFragment
        }
        val fragment: Fragment = createFragmentBy(position)
        fragmentsCache[position] = fragment
        return fragment
    }

    private fun createFragmentBy(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment.create()
            }
            1 -> {
                FormFragment.create(formModel)
            }
            2 -> {
                CustomListFragment.create(customListModel)
            }
            else -> {
                throw UnknownFragmentException()
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        fragmentsCache[position] = null
        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int {
        return ITEMS_COUNT
    }

    companion object {

        private const val ITEMS_COUNT = 3
    }
}