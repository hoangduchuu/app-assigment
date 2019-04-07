package com.hdhuu.data.post.mapper

import com.hdhuu.data.post.models.CandyDTO
import com.hdhuu.domain.models.CandyEntity

/**
 * Created by Huu Hoang on 4/3/19.
 */
class CandyMapper : BaseMapper<CandyDTO, CandyEntity>() {
    override fun mapToDTO(o2: CandyEntity): CandyDTO {
        val dto = CandyDTO()
        dto.eatingCount = o2.eatingCount!!
        dto.id = o2.id!!
        dto.color = o2.color!!
        return dto
    }

    override fun mapToEntity(o1: CandyDTO): CandyEntity {
        val entity = CandyEntity()
        entity.eatingCount = o1.eatingCount
        entity.id = o1.id
        entity.color = o1.color
        return entity
    }
}