package edu.ucne.erick_p1_ap2.data.local.repository

import javax.inject.Inject

class  TecnicoRepository @Inject constructor(
    private val tecnicoao: Dao
) {
    suspend fun save(ticket: dao) = tecnicoDao.save(ticket)

    suspend fun get(id: Int) = tecnicoDao.find(id)

    suspend fun delete(ticket: TecnicoEntity) = tecnicoDao.delete(ticket)

    fun getAll() = tecnicoDao.getAll()
}