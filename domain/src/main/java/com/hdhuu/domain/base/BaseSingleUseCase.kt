package com.hdhuu.domain.base

import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Huu Hoang on 3/29/19.
 */

/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class BaseSingleUseCase<RESPONSE_DATA, in INPUT_DATA> constructor(
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
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

    private val disposables = CompositeDisposable()

    /**
     * Builds a [Single] which will be used when the current [BaseSingleUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: INPUT_DATA? = null): Single<RESPONSE_DATA>

    /**
     * Executes the current use case.
     */
    open fun execute(singleObserver: DisposableSingleObserver<RESPONSE_DATA>, params: INPUT_DATA? = null) {
        val single = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(mThreadExecutor))
            .observeOn(mPostExecutionThread.scheduler) as Single<RESPONSE_DATA>
        addDisposable(single.subscribeWith(singleObserver))
    }

    open fun run(params: INPUT_DATA, observer: DisposableSingleObserver<RESPONSE_DATA>) {
        val observable = run(params)
        if (observer != null)
            addDisposable(observable.subscribeWith(observer))
        else
            observable.subscribe()
    }

    private fun run(params: INPUT_DATA): Single<RESPONSE_DATA> {
        return buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(mPostExecutionThread.scheduler) as Single<RESPONSE_DATA>
    }


    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}