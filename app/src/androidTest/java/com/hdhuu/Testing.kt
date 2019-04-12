package com.hdhuu

import androidx.test.runner.AndroidJUnit4
import com.hdhuu.data.post.mapper.CandyViewMapper
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.repository.CandyRepository
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import com.hdhuu.domain.usecase.CreateCandyUsecase
import com.hdhuu.domain.usecase.GetCandyUsecase
import com.hdhuu.domain.usecase.IncreaseCounterUsecase
import com.hdhuu.home.MainContract
import com.hdhuu.home.MainPresenter
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Created by Huu Hoang on 4/8/19.
 */

@RunWith(AndroidJUnit4::class)
class Testing {

    private val mockThreadExecutor = mock<ThreadExecutor>()
    private val mockPostExecutionThread = mock<PostExecutionThread>()
    private val candyRepository = mock<CandyRepository>()
    lateinit var presenter: MainContract.Presenter
    @Mock
    lateinit var view: MainContract.View
    @Mock
    lateinit var createCandyUsecase: CreateCandyUsecase
    private var getCandyUsecase = GetCandyUsecase(mockThreadExecutor, mockPostExecutionThread, candyRepository)

    @Mock
    lateinit var candyViewMapper: CandyViewMapper
    private var increaseCounterUsecase =
        IncreaseCounterUsecase(mockThreadExecutor, mockPostExecutionThread, candyRepository)


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, createCandyUsecase, getCandyUsecase, candyViewMapper, increaseCounterUsecase)
    }


    @Test
    fun getCandyUsecaseThenReturnData() {
        val candies = DataFactory.makeCandyLIst(11)
        stuffGetCandy(Observable.just(candies))
        val testObserver = getCandyUsecase.buildUseCaseObservable("123").test()
        testObserver.assertValue(candies)
    }

    @Test
    fun increaseCounter() {
        stubIncreaseCounter()
        val testObserver = increaseCounterUsecase
            .buildUseCaseCompletable(IncreaseCounterUsecase.Params(1, 2)).test()
        testObserver.assertComplete()
    }

    @Test // FIXME can not testing this case
    fun testPresenterUpdateView() {
        val candyLIst = DataFactory.makeCandyLIst(12)
        stubGetData(Observable.just(candyLIst))
        presenter.getCandies()
        verify(view).hideEmptyView()
    }

    private fun stubGetData(data: Observable<List<CandyEntity>>) {
        whenever(getCandyUsecase.buildUseCaseObservable("123"))
            .thenReturn(data)

    }

    private fun stuffGetCandy(obsData: Observable<List<CandyEntity>>) {
        whenever(candyRepository.getAllCandy())
            .thenReturn(obsData)
    }

    private fun stubIncreaseCounter() {
        whenever(candyRepository.increaseCandyCount(1, 2))
            .thenReturn(Completable.complete())
    }

}