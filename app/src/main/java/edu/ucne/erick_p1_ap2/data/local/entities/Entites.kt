package edu.ucne.erick_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dao")
data class TecnicoEntity(
    @PrimaryKey(autoGenerate = true)
    val entitiesId: Int? = null,
    val  tablej: String = "",
    val entities: Double = 0.0
)