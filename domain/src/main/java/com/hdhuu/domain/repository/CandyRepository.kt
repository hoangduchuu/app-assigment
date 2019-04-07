package com.hdhuu.domain.repository

import com.hdhuu.domain.models.CandyEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Huu Hoang on 3/29/19.
 */

interface CandyRepository {
    fun getAllCandy(): Observable<List<CandyEntity>>

    fun increaseCandyCount(candyID: Int,currentCount: Int): Observable<List<CandyEntity>>
    fun insertCandy(): Completable
}