package edu.ucne.erick_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{

    @Serializable

    data object  HomeScreen: Screen()

    @Serializable
    data object IndexSistemaScreen : Screen()
    @Serializable
    data object CreateSistemaScreen : Screen()
    @Serializable
    data class EditSistemaScreen(val sistemaId: Int?) : Screen()
    @Serializable
    data class DeleteSistemaScreen(val sistemaId: Int?) : Screen()
}