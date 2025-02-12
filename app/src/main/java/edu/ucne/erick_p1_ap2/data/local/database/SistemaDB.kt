package edu.ucne.erick_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.erick_p1_ap2.data.local.dao.SistemaDao

@Database(
    entities = [SistemaEntity::class],
    version = 3,
    exportSchema = false
)
abstract class SistemaDB : RoomDatabase() {
    abstract fun sistemaDao(): SistemaDao
}
