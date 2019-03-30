package com.hdhuu.data.repository

import com.hdhuu.data.post.models.CandyDTO
import com.hdhuu.data.room.AppDatabase
import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.domain.repository.CandyRepository
import io.reactivex.Observable

/**
 * Created by Huu Hoang on 3/29/19.
 */

class CandyRepositoryImpl(val roomDB: AppDatabase, val mapper: CandyDTO.CandyMapper) : CandyRepository {

    // after any process success, we always re-get the data
    // beacause we need to clone long long long processes
    override fun getAllCandy(): Observable<List<CandyEntity>> {
        return Observable.create { emitter ->
            roomDB.candyDAO().getAllSugar().doOnNext {
                emitter.onNext(mapper.mapToEntity(it))
            }.doOnComplete {
                emitter.onComplete()
            }
                .doOnError { e -> emitter.onError(e) }
        }
    }

    override fun increaseCandyCount(candyID: Int, currentCount: Int): Observable<List<CandyEntity>> {
        return Observable.create { emitter ->
            roomDB.candyDAO().increaseEatingCount(candyID, currentCount + 1)
                .doOnComplete {
                    roomDB.candyDAO().getAllSugar().doOnNext {
                        emitter.onNext(mapper.mapToEntity(it))
                    }
                        .doOnComplete { emitter.onComplete() }
                        .doOnError { e -> emitter.onError(e) }
                }
                .doOnError { e -> emitter.onError(e) }
        }
    }

}



