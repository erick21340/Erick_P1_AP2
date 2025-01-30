package edu.ucne.erick_p1_ap2.di

import android.content.Context

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object  AppModule {

    @Provides
    @Singleton
    fun provideTecnicoDB(@ApplicationContext appContext: Context) = appContext


}