package com.hdhuu.data.post.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hdhuu.data.post.mapper.BaseMapper
import com.hdhuu.domain.models.CandyEntity


@Entity(tableName = "candy")
data class CandyDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = -1,

    var eatingCount: Int = 0

)
