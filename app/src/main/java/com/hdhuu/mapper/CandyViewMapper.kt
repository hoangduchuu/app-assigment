package com.hdhuu.data.post.mapper

import com.hdhuu.domain.models.CandyEntity
import com.hdhuu.models.Candy

/**
 * Created by Huu Hoang on 4/8/19.
 */
open class CandyViewMapper : BaseMapper<Candy, CandyEntity>() {
    override fun mapToViewModel(o2: CandyEntity): Candy {
        val dto = Candy()
        dto.eatingCount = o2.eatingCount!!
        dto.id = o2.id
        dto.color =o2.color
        return dto
    }

    override fun mapToEntity(o1: Candy): CandyEntity {
        val entity = CandyEntity()
        entity.eatingCount = o1.eatingCount
        entity.id = o1.id
        entity.color = o1.color
        return entity
    }
}