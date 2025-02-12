package edu.ucne.erick_p1_ap2.presentation.navigation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.erick_p1_ap2.presentation.navigation.HomeScreen
import edu.ucne.erick_p1_ap2.presentation.navigation.Screen
import edu.ucne.erick_p1_ap2.presentation.navigation.sistema.CreateSistemaScreen

import edu.ucne.erick_p1_ap2.presentation.navigation.sistema.EditSistemaScreen
import edu.ucne.erick_p1_ap2.presentation.navigation.sistema.IndexSistemaScreen
import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.DeleteSistemaScreen


@Composable
fun NavigationNavHost1(
    navHostController: NavHostController
) {
    val isDrawerVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navHostController, startDestination = Screen.HomeScreen) {

            // Home
            composable<Screen.HomeScreen> {
                HomeScreen(
                    onDrawerToggle = { isDrawerVisible.value = !isDrawerVisible.value }
                )
            }

            composable<Screen.IndexSistemaScreen> {
                IndexSistemaScreen (
                    onDrawerToggle = { isDrawerVisible.value = !isDrawerVisible.value },
                    onCreateSistema = {
                        navHostController.navigate(Screen.CreateSistemaScreen)
                    },
                    onEditSistema = {
                        navHostController.navigate(Screen.EditSistemaScreen(it))
                    },
                    onDeleteSistema = {
                        navHostController.navigate(Screen.DeleteSistemaScreen(it))
                    }
                )
            }

            composable<Screen.CreateSistemaScreen> {
                CreateSistemaScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToSistema = {
                        navHostController.navigate(Screen.IndexSistemaScreen)
                    }
                )
            }

            composable<Screen.EditSistemaScreen> { backStackEntry ->
                val sistemaId = backStackEntry.arguments?.getInt("sistemaId")
                if (sistemaId != null) {

                    EditSistemaScreen (
                        sistemaId = sistemaId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goTosistema = {
                            navHostController.navigate(Screen.IndexSistemaScreen)
                        }
                    )
                }
            }

            composable<Screen.DeleteSistemaScreen> { backStackEntry ->
                val sistemaId = backStackEntry.arguments?.getInt("sistemaId")
                if (sistemaId != null) {

                    DeleteSistemaScreen(
                        sistemaId = sistemaId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goTosistema = {
                            navHostController.navigate(Screen.IndexSistemaScreen)
                        }
                    )
                }
            }






        }
    }

    //
    //
    //
    //
    //
    //
    //
    //
    // Menú lateral
    NavigationDrawer(
        isVisible = isDrawerVisible.value,
        onItemClick = { itemTitle ->
            when (itemTitle) {
                "Inicio" -> navHostController.navigate(Screen.HomeScreen)
                "Sistema" -> navHostController.navigate(Screen.IndexSistemaScreen)
            }
            isDrawerVisible.value = false
        },
        onClose = {
            isDrawerVisible.value = false
        }
    )
    }
