package com.hdhuu.home

import com.hdhuu.data.post.mapper.CandyViewMapper
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.usecase.CreateCandyUsecase
import com.hdhuu.domain.usecase.GetCandyUsecase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver

/**
 * Created by Huu Hoang on 4/3/19.
 */

class MainPresenter(
    val view: MainContract.View, private val createCandyUseCase: CreateCandyUsecase,
    private val getCandyUseCase: GetCandyUsecase, val candyMapper: CandyViewMapper
) : MainContract.Presenter {
    override fun getCandies() {
        getCandyUseCase.run("", object : DisposableObserver<List<CandyEntity>>() {
            override fun onComplete() {}

            override fun onNext(t: List<CandyEntity>) {
                if (t.isEmpty()) {
                    view.showEmptyView()
                    return
                }
                view.onGetDataSuccess(candyMapper.mapToViewModel(t))
                view.hideEmptyView()
            }

            override fun onError(e: Throwable) {}
        })


    }

    override fun createSampleCandies() {
        createCandyUseCase.run("", object : DisposableCompletableObserver() {
            override fun onComplete() {
                view.fetchData()
            }

            override fun onError(e: Throwable) {}

        })
    }

}