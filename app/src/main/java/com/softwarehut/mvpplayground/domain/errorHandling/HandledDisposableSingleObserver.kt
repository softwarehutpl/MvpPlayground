package com.softwarehut.mvpplayground.domain.errorHandling

import io.reactivex.observers.DisposableSingleObserver

abstract class HandledDisposableSingleObserver<TData> : DisposableSingleObserver<TData>() {

    override fun onError(e: Throwable) {
        //handle your errors, for ex via logging or popups
    }
}