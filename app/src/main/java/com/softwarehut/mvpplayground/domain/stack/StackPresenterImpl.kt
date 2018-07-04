package com.softwarehut.mvpplayground.domain.stack

import com.softwarehut.mvp.domain.paramless.BasePresenter
import com.softwarehut.mvpplayground.domain.customList.CustomListItem
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import com.softwarehut.mvpplayground.domain.customList.CustomListModelRepository
import com.softwarehut.mvpplayground.domain.customList.EmptyCustomListModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.util.*


class StackPresenterImpl(override val view: StackView,
                         val customListModelRepository: CustomListModelRepository) :
        BasePresenter(view),
        StackPresenter {

    val random: Random = Random()

    override fun onPushToStackButtonClick() {
        val id = random.nextLong()
        val customListItem = EmptyCustomListModel(id)
        customListModelRepository.store(customListItem)
        view.pushToStack(customListItem)
    }

    override fun addItemsClick() {
        val items = view.items
        for (item in items) {
            customListModelRepository.restore(item.id).subscribe(object : SingleObserver<CustomListModel> {

                override fun onSuccess(model: CustomListModel) {
                    model.items.add(CustomListItem(random.nextLong()))
                    customListModelRepository.store(model)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
        }
    }
}