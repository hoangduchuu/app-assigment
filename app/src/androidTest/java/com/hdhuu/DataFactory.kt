package com.hdhuu

import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.models.Candy
import java.util.*

/**
 * Created by Huu Hoang on 4/12/19.
 */
object DataFactory {
    fun makeCandy(): CandyEntity {
        return CandyEntity(Random().nextInt(3), Random().nextInt(10), Random().nextInt(3))

    }

    fun makeCandyLIst(count: Int): List<CandyEntity> {
        return (0..count).map { makeCandy() }
    }

    fun makeCandyVM(): Candy {
        return Candy(Random().nextInt(3), Random().nextInt(10), Random().nextInt(3))

    }

    fun makeCandyVMLIst(count: Int): List<Candy> {
        return (0..count).map { makeCandyVM() }
    }
}