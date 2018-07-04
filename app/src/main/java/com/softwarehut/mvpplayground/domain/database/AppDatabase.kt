package com.softwarehut.mvpplayground.domain.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.softwarehut.mvpplayground.domain.customList.CustomListModel
import com.softwarehut.mvpplayground.domain.customList.CustomListModelDao

@Database(entities = [(CustomListModel::class)], version = 1)
@TypeConverters(CustomListModelDao.Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customListModelDao(): CustomListModelDao
}