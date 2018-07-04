package com.softwarehut.mvpplayground.domain.stack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.softwarehut.mvp.domain.parametrized.ParamsActivity
import com.softwarehut.mvpplayground.R
import com.softwarehut.mvpplayground.domain.customList.CustomListFragment
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import kotlinx.android.synthetic.main.activity_stack.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.newInstance
import java.util.*

class StackActivity : ParamsActivity<StackPresenter>(), StackView {

    override val items: Stack<CustomListModel> = Stack()

    private val kodein by closestKodein()

    private val backStackTransactionName = "NAME"

    override fun createPresenter(): StackPresenter {
        val bindingContainer = kodein
        val newInstance = bindingContainer.newInstance { StackPresenterImpl(this@StackActivity, instance()) }
        val presenter by newInstance
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        floatingActionButton2.setOnClickListener {
            popFromStack()
        }

        floatingActionButton3.setOnClickListener {
            presenter.onPushToStackButtonClick()
        }


        addItemsButton.setOnClickListener {
            presenter.addItemsClick()
        }
    }

    override fun getContentView(): Int {
        return R.layout.activity_stack
    }

    override fun pushToStack(customListModel: CustomListModel) {
        items.push(customListModel)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentsContainer, CustomListFragment.create(customListModel))
                .addToBackStack(backStackTransactionName)
                .commit()
    }

    override fun popFromStack() {
        if(!items.empty()) {
            items.pop()
        }
        supportFragmentManager.popBackStack()
    }

    companion object {

        fun navigate(activity: Activity, params: Bundle) {
            val intent = Intent(activity.baseContext, StackActivity::class.java)
            putParams(intent, params)
            activity.startActivity(intent)
        }
    }
}