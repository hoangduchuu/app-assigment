package com.hdhuu.domain.usecase

import com.hdhuu.domain.base.BaseCompletableUseCase
import com.hdhuu.domain.base.BaseObservableUseCase
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.repository.CandyRepository
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Observable

open class IncreaseCounterUsecase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val repo: CandyRepository) : BaseCompletableUseCase<IncreaseCounterUsecase.Params>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseCompletable(params: Params?): Completable {
        return repo.increaseCandyCount(params?.candyID!!,params.currentCount)

    }

    class Params(val candyID: Int, val currentCount: Int)
}