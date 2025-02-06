package edu.ucne.erick_p1_ap2.di

import android.content.Context

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import edu.ucne.erick_p1_ap2.data.local.database.SistemaDB


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSistemaDB(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            SistemaDB::class.java,
            "SistemaDB.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSistemaDao(sistemaDB: SistemaDB) = sistemaDB.sistemaDao()
}