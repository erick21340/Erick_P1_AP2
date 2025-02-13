package edu.ucne.erick_p1_ap2.data.local.repository

import edu.ucne.erick_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun save(sistema: SistemaEntity) = sistemaDao.save(sistema)

    suspend fun update(sistema: SistemaEntity) = sistemaDao.update(sistema)

    suspend fun delete(sistema: SistemaEntity) = sistemaDao.delete(sistema)

    suspend fun get(id: Int): SistemaEntity? = sistemaDao.get(id)

    fun getAll(): Flow<List<SistemaEntity>> = sistemaDao.getAll()
}
