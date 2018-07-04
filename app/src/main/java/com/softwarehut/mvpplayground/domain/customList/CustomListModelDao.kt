package com.softwarehut.mvpplayground.domain.customList

import android.arch.persistence.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CustomListModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg customListModels: CustomListModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(customListModel: CustomListModel)

    @Query("SELECT * FROM customlistmodel WHERE id = :id")
    fun getById(id: String): Single<CustomListModel>

    @Query("SELECT * FROM customlistmodel WHERE id = :id")
    fun existsById(id: String): Single<List<CustomListModel>>

    @Query("SELECT * FROM customlistmodel WHERE id = :id")
    fun getByIdStream(id: String): Flowable<CustomListModel>

    class Converters {
        @TypeConverter
        fun fromString(value: String): ArrayList<CustomListItem> {
            val listType = object : TypeToken<ArrayList<CustomListItem>>() {

            }.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: ArrayList<CustomListItem>): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}