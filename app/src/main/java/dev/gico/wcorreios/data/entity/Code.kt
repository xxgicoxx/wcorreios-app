package dev.gico.wcorreios.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Code (
    @PrimaryKey var code: String
)
