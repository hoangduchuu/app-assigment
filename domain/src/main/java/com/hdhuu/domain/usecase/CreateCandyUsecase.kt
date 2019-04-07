package com.hdhuu.domain.usecase

import com.hdhuu.domain.base.BaseCompletableUseCase
import com.hdhuu.domain.base.BaseObservableUseCase
import com.hdhuu.domain.repository.CandyRepository
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Observable

open class CreateCandyUsecase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val repo: CandyRepository) : BaseCompletableUseCase<Any?>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseCompletable(params: Any?): Completable {
       return repo.insertCandy()
    }
}