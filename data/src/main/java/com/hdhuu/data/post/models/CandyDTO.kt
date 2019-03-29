package com.hdhuu.data.post.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "candy")
data class CandyDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var eatingCount: Int = 0

) {
    constructor(content: Int) : this() {
        this.eatingCount = content
    }
}