package com.hdhuu.domain.base

import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Huu Hoang on 3/29/19.
 */

abstract class BaseCompletableUseCase<in INPUT_DATA> constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) {
    /**
     * The thread to execute the business logic. It will be the backend thread to prevent locking
     * main thread.
     */
    var mThreadExecutor = threadExecutor;
    /**
     * The thread that receive result after executing business logic. It maybe main thread if we
     * need to update UI or any thread. It's belong to specific uses
     */
    var mPostExecutionThread = postExecutionThread

    /**
     * Use this to make the use-case cancellable anytime.
     */
    private val disposables = CompositeDisposable()


    abstract fun buildUseCaseCompletable(params: INPUT_DATA? = null): Completable
    open fun execute(observer: DisposableCompletableObserver, params: INPUT_DATA? = null) {
        val completable = this.buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(mPostExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(observer))
    }

    open fun run(params: INPUT_DATA, observer: DisposableCompletableObserver) {
        val observable = run(params)
        if (observer != null)
            addDisposable(observable.subscribeWith(observer))
        else
            observable.subscribe()
    }

    private fun run(params: INPUT_DATA): Completable {
        return buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(mPostExecutionThread.scheduler)
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}