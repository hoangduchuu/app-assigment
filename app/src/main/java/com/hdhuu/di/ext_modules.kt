package com.hdhuu.di

import com.hdhuu.data.post.mapper.CandyMapper
import com.hdhuu.data.post.mapper.CandyViewMapper
import com.hdhuu.data.repository.CandyRepositoryImpl
import com.hdhuu.data.room.AppDatabase
import com.hdhuu.domain.repository.CandyRepository
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import com.hdhuu.domain.usecase.CreateCandyUsecase

import com.hdhuu.domain.usecase.GetCandyUsecase
import com.hdhuu.domain.usecase.IncreaseCounterUsecase
import com.hdhuu.home.MainContract
import com.hdhuu.home.MainPresenter
import com.hdhuu.scheduler.JobExecutor
import com.hdhuu.scheduler.UIThread
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Huu Hoang on 3/29/19.
 */
/**
 * any modules will be located over here
 */

val applicationModule = module(override = true) {
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }
    factory { AppDatabase.getInstance(androidContext()) }
    factory { get<AppDatabase>().candyDAO() }
    factory { CandyMapper() }
    factory<CandyRepository> { CandyRepositoryImpl(get(), get()) }
    factory { CreateCandyUsecase(get(), get(), get()) }
    factory { GetCandyUsecase(get(), get(), get()) }
    factory { IncreaseCounterUsecase(get(), get(), get()) }
    factory { CandyViewMapper() }
    factory<MainContract.Presenter> { (cv: MainContract.View) ->
        MainPresenter(cv, get(), get(),get(),get())
    }

}