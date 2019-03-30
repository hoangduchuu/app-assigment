package com.hdhuu.domain.usecase

import com.hdhuu.domain.base.BaseObservableUseCase
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.repository.CandyRepository
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import io.reactivex.Observable

open class GetCandyUsecase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val repo: CandyRepository) : BaseObservableUseCase<String, List<CandyEntity>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String): Observable<List<CandyEntity>> {
        return repo.getAllCandy()
    }
}