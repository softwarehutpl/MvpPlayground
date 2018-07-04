package com.softwarehut.mvpplayground.domain.errorHandling

import io.reactivex.observers.DisposableObserver

abstract class HandledDisposableObserver<TData> : DisposableObserver<TData>() {

    override fun onError(e: Throwable) {
        //handle your errors, for ex via logging or popups
    }
}