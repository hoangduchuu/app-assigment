package com.hdhuu.data.room


import androidx.room.*
import com.hdhuu.data.post.models.CandyDTO

@Dao
abstract class CandyDAO {

    @Query("select * from candy")
    abstract fun getAllSugar(): List<CandyDTO>

    @Query("update candy  set eatingCount =:eatingCount where id =:sugarId")
    abstract fun increaseEatingCount(sugarId: Int, eatingCount: Int)


    // MAYBE USE
    @Query("DELETE FROM candy")
    abstract fun clearCandy()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCandy(candyDTO: CandyDTO)

    @Query(" delete from candy where id =:sugarId")
    abstract fun removeSugarById(sugarId: Int)

    @Delete
    abstract fun remoteCandy(candyDTO: CandyDTO)

    @Update
    abstract fun updateCandy(candyDTO: CandyDTO)


}