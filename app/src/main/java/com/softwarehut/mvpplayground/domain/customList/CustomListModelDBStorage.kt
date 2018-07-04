package com.softwarehut.mvpplayground.domain.customList

import com.softwarehut.mvpplayground.domain.threading.RxScheduling
import com.softwarehut.shrepository.domain.storage.ReactiveStorage
import com.softwarehut.shrepository.domain.storage.disk.DiskSaver
import io.reactivex.Observable
import io.reactivex.Single

class CustomListModelDBStorage(val dao: CustomListModelDao) : ReactiveStorage<CustomListModel> {
    override fun exists(id: String): Single<Boolean> {
        return dao.existsById(id)
                .compose(RxScheduling.ioSingle())
                .map {
                    !it.isEmpty()
                }
    }

    override fun changes(id: String): Observable<CustomListModel> {
        return dao.getByIdStream(id).toObservable().compose(RxScheduling.ioObservable())
    }

    private val diskSaver = object : DiskSaver<CustomListModel> {
        override fun save(id: String, data: CustomListModel) {
            if(id.toLong() != data.id) {
                throw IllegalStateException("Wrong ID passed to storage!")
            }
            val insertStream = Observable.fromCallable {
                dao.insert(data)
            }
            insertStream.compose(RxScheduling.ioObservable()).subscribe()
        }
    }

    override fun diskSaver(): DiskSaver<CustomListModel> = diskSaver

    override fun readStream(id: String, type: Class<CustomListModel>): Observable<CustomListModel> =
            dao.getById(id).toObservable().compose(RxScheduling.ioObservable())
}