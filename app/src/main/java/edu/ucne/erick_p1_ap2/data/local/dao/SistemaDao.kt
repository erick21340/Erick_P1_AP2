package edu.ucne.erick_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import kotlinx.coroutines.flow.Flow




@Dao
interface SistemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(sistema: SistemaEntity)

    @Update
    suspend fun update(sistema: SistemaEntity)

    @Delete
    suspend fun delete(sistema: SistemaEntity)

    @Query("SELECT * FROM Sistema WHERE sistemaId = :id LIMIT 1")
    suspend fun get(id: Int): SistemaEntity?

    @Query("SELECT * FROM Sistema")
    fun getAll(): Flow<List<SistemaEntity>>
}
