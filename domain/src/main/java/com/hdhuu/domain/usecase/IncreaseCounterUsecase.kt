package com.hdhuu.domain.usecase

import com.hdhuu.domain.base.BaseObservableUseCase
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.repository.CandyRepository
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import io.reactivex.Observable

open class IncreaseCounterUsecase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val repo: CandyRepository) : BaseObservableUseCase<IncreaseCounterUsecase.Params, List<CandyEntity>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<CandyEntity>> {
        return repo.increaseCandyCount(params.candyID,params.currentCount)
    }

    class Params(val candyID: Int, val currentCount: Int)
}