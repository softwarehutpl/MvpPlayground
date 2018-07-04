package com.softwarehut.mvpplayground.domain.threading

import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxScheduling {

    fun <T> ioObservable(): ObservableTransformer<T, T> =
            ObservableTransformer { observable ->
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }

    fun <T> ioSingle(): SingleTransformer<T, T> =
            SingleTransformer { observable ->
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
}