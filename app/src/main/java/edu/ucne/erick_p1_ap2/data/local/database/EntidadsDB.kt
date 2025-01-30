package edu.ucne.erick_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        dao::class,

    ],
    version = 2,
    exportSchema = false
)
abstract class  daoDB : RoomDatabase() {
    abstract fun dao ():dao
    abstract fun ticketDao(): TicketDao
}