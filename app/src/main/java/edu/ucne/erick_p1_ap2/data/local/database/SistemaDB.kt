package edu.ucne.erick_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.erick_p1_ap2.data.local.dao.SistemaDao  // Asegúrate de importar tu DAO correctamente

@Database(
    entities = [SistemaEntity::class],
    version = 2,
    exportSchema = false
)
abstract class SistemaDB : RoomDatabase() {
    abstract fun sistemaDao(): SistemaDao
}
