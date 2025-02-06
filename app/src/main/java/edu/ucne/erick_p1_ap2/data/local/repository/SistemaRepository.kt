package edu.ucne.erick_p1_ap2.data.local.repository

import edu.ucne.erick_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun save(sistema: SistemaEntity) {
        sistemaDao.save(sistema)
    }

    suspend fun find(id: Int): SistemaEntity? {
        return sistemaDao.find(id)
    }

    suspend fun delete(sistema: SistemaEntity) {
        sistemaDao.delete(sistema)
    }

    fun getAll(): Flow<List<SistemaEntity>> {
        return sistemaDao.getAll()
    }
}
