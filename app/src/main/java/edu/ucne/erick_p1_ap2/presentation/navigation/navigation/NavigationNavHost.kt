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
import edu.ucne.erick_p1_ap2.presentation.navigation.sistema.IndexSistemaScreen



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
                    onDrawerToggle = { isDrawerVisible.value = !isDrawerVisible.value }
                )
            }









        }
    }

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
