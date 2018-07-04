package com.softwarehut.mvpplayground.domain.customList

import com.softwarehut.mvp.domain.paramless.BasePresenter
import com.softwarehut.mvpplayground.domain.errorHandling.HandledDisposableObserver
import com.softwarehut.mvpplayground.domain.errorHandling.HandledDisposableSingleObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class CustomListPresenterImpl(override val view: CustomListView,
                              private val repository: CustomListModelRepository) :
        BasePresenter(view),
        CustomListPresenter {

    private val random: Random = Random()

    private lateinit var model: CustomListModel

    override fun requestItems(initialModel: CustomListModel) {
        model = initialModel

        val onListRestored: DisposableSingleObserver<CustomListModel> =
                object : HandledDisposableSingleObserver<CustomListModel>() {

                    override fun onSuccess(t: CustomListModel){
                        view.setListItems(t.items)
                        model = t
                    }
                }

        foregroundDisposable.add(onListRestored)

        repository.contains(model.id).subscribe(object : HandledDisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                if(t) {
                    repository.restore(model.id).subscribe(onListRestored)
                } else {
                    view.setListItems(model.items)
                    repository.store(model)
                }
            }
        })

        val onListReplacedChanged: DisposableObserver<CustomListModel> =
                object : HandledDisposableObserver<CustomListModel>() {

                    override fun onComplete() {}

                    override fun onNext(t: CustomListModel) =
                            view.setListItems(t.items)
                }

        foregroundDisposable.add(onListReplacedChanged)
        repository.listenForData(model.id).subscribe(onListReplacedChanged)
    }

    override fun onAddClick() {

        repository.contains(model.id).subscribe(object: HandledDisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                if(t) {
                    repository.restore(model.id).subscribe({ model ->
                        addListItemAndDisplay(model)
                    })
                } else {
                    addListItemAndDisplay(model)
                }
            }
        })
    }

    private fun addListItemAndDisplay(model: CustomListModel) {
        model.items.add(CustomListItem(random.nextLong()))
        view.setListItems(ArrayList(model.items))
        this.model = model
        repository.store(model)
    }
}