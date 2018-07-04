package com.softwarehut.mvpplayground.domain.customList

import com.softwarehut.shrepository.domain.repository.SourcedRepository
import io.reactivex.Observable
import io.reactivex.Single

class CustomListModelRepository(private val repository: SourcedRepository<CustomListModel>) {

    fun restore(id: Long): Single<CustomListModel> =
            repository.request(mapId(id), CustomListModel::class.java)

    fun store(customListItem: CustomListModel) =
            repository.store(mapId(customListItem.id), customListItem, CustomListModel::class.java)

    fun listenForData(id: Long): Observable<CustomListModel> =
            repository.observe(mapId(id), CustomListModel::class.java)

    fun contains(id: Long): Single<Boolean> =
            repository.exists(mapId(id))

    private fun mapId(id: Long) = id.toString()
}