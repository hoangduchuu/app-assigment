package com.hdhuu.data.repository

import com.hdhuu.data.post.mapper.CandyMapper
import com.hdhuu.data.post.models.CandyDTO
import com.hdhuu.data.room.AppDatabase
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.repository.CandyRepository
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Huu Hoang on 3/29/19.
 */

class CandyRepositoryImpl(val roomDB: AppDatabase, val mapper: CandyMapper) : CandyRepository {

    // after any process success, we always re-get the data
    // beacause we need to clone long long long processes
    override fun getAllCandy(): Observable<List<CandyEntity>> {
        return roomDB.candyDAO().getAllSugar().toObservable().map {
            mapper.mapToEntity(it)
        }
    }

    override fun increaseCandyCount(candyID: Int, currentCount: Int): Completable {
        return roomDB.candyDAO().increaseEatingCount(candyID,eatingCount = currentCount+1)
    }

    override fun insertCandy(): Completable {
        val data = mutableListOf<CandyDTO>()
        for (x in 0..10){
            data.add(CandyDTO(null,0))
        }

        // insert sample candy if needed
        return roomDB.candyDAO().insertMultiCandy(data)
    }

}



