package com.softwarehut.mvpplayground.domain.main

import com.softwarehut.mvp.domain.paramless.BasePresenter
import com.softwarehut.mvpplayground.domain.customList.CustomListModelRepository
import com.softwarehut.mvpplayground.domain.customList.EmptyCustomListModel
import com.softwarehut.mvpplayground.domain.form.EmptyFormModel
import java.util.*

class MainPresenterImpl(override val view: MainView,
                        private val customListModelRepository: CustomListModelRepository) :
        BasePresenter(view),
        MainPresenter {

    override fun initialized() {
        super.initialized()
        val customListItem = EmptyCustomListModel(Random().nextLong())
        customListModelRepository.store(customListItem)
        view.initScreens(EmptyFormModel(), customListItem)
    }

    override fun onNavigateToHome() {
        view.navigateToHome()
    }

    override fun onNavigateToForm() {
        view.navigateToForm()
    }

    override fun onNavigateToCustomList() {
        view.navigateToCustomList()
    }

    override fun onNavigateToIntroductionLogin() {
        view.navigateToIntroductionLogin()
    }

    override fun onNavigateToStack() {
        view.navigateToStack()
    }
}