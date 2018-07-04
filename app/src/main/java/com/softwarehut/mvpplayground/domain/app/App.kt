package com.softwarehut.mvpplayground.domain.app

import android.app.Application
import android.arch.persistence.room.Room
import com.softwarehut.mvpplayground.domain.customList.CustomListModelDBStorage
import com.softwarehut.mvpplayground.domain.customList.CustomListModelDao
import com.softwarehut.mvpplayground.domain.customList.CustomListModelRepository
import com.softwarehut.mvpplayground.domain.customList.CustomListModelWebStorage
import com.softwarehut.mvpplayground.domain.database.AppDatabase
import com.softwarehut.mvpplayground.domain.logging.LoggingService
import com.softwarehut.mvpplayground.domain.logging.LoggingServiceImpl
import com.softwarehut.shrepository.domain.repository.SourcedRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidModule(this@App))

        bind<LoggingService>() with singleton { LoggingServiceImpl() }

        bind<AppDatabase>() with singleton {
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "AppDB").build()
        }

        bind<CustomListModelDao>() with singleton {
            val db: AppDatabase = instance()
            db.customListModelDao()
        }

        bind<CustomListModelDBStorage>() with singleton {
            CustomListModelDBStorage(instance())
        }

        bind<CustomListModelWebStorage>() with singleton {
            CustomListModelWebStorage()
        }

        bind<CustomListModelRepository>() with singleton {
            val webStorage: CustomListModelWebStorage = instance()
            val diskStorage: CustomListModelDBStorage = instance()
            CustomListModelRepository(SourcedRepository(diskStorage, webStorage))
        }
    }
}