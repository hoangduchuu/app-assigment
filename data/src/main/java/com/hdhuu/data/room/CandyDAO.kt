package com.hdhuu.data.room


import androidx.room.*
import com.hdhuu.data.post.models.CandyDTO
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class CandyDAO {

    @Query("select * from candy")
    abstract fun getAllSugar(): Flowable<List<CandyDTO>>

    @Query("update candy  set eatingCount =:eatingCount where id =:sugarId")
    abstract fun increaseEatingCount(sugarId: Int, eatingCount: Int): Completable


    // MAYBE USE
    @Query("DELETE FROM candy")
    abstract fun clearCandy()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: CandyDTO): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCandy(candyDTO: CandyDTO): Completable

    @Insert
    abstract fun insertMultiCandy(candies : List<CandyDTO>): Completable

    @Query(" delete from candy where id =:sugarId")
    abstract fun removeSugarById(sugarId: Int)

    @Delete
    abstract fun remoteCandy(candyDTO: CandyDTO)

    @Update
    abstract fun updateCandy(candyDTO: CandyDTO)


}