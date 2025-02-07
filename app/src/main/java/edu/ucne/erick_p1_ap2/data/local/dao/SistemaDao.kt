package edu.ucne.erick_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import kotlinx.coroutines.flow.Flow




@Dao
interface SistemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(sistema: SistemaEntity)

    @Query("SELECT * FROM Sistema WHERE sistemaid = :sistemaid")
    suspend fun find(sistemaid: Int): SistemaEntity?

    @Delete
    suspend fun delete(sistema: SistemaEntity)

    @Query("SELECT * FROM Sistema")
    fun getAll(): Flow<List<SistemaEntity>>
}
