package com.softwarehut.mvpplayground.domain.customList

import com.softwarehut.mvpplayground.domain.threading.RxScheduling
import com.softwarehut.shrepository.domain.storage.WebReactiveStorage
import io.reactivex.Observable

class CustomListModelWebStorage : WebReactiveStorage<CustomListModel> {

    override fun readStream(id: String): Observable<CustomListModel> {
        val yourRetrofitObservable: Observable<CustomListModel> = Observable.fromCallable {
            EmptyCustomListModel(id.toLong())
        }.compose(RxScheduling.ioObservable())
        return yourRetrofitObservable
    }
}