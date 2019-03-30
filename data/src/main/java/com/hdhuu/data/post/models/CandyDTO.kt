package com.hdhuu.data.post.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hdhuu.data.post.mapper.BaseMapper
import com.hdhuu.domain.models.CandyEntity


@Entity(tableName = "candy")
data class CandyDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var eatingCount: Int = 0

) {
    constructor(content: Int) : this() {
        this.eatingCount = content
    }

    class CandyMapper : BaseMapper<CandyDTO, CandyEntity>() {
        override fun mapToDTO(o2: CandyEntity): CandyDTO {
            val dto = CandyDTO()
            dto.eatingCount = o2.eatingCount!!
            dto.id = o2.id!!
            return dto
        }

        override fun mapToEntity(o1: CandyDTO): CandyEntity {
            val entity = CandyEntity()
            entity.eatingCount = o1.eatingCount
            entity.id = o1.id
            return entity
        }


    }
}