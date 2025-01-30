package edu.ucne.erick_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface  Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(dao: Dao)

    @Query("SELECT * FROM dao WHERE dao = :id")
    suspend fun find(id: Int): dao?

    @Delete
    suspend fun delete(dao: Dao)

    @Query("SELECT * FROM dao")
    fun getAll(): Flow<List<dao>>
}