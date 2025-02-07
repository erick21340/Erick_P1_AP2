package edu.ucne.erick_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.erick_p1_ap2.presentation.navigation.navigation.NavigationNavHost1

import edu.ucne.erick_p1_ap2.ui.theme.Erick_P1_AP2Theme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Erick_P1_AP2Theme {
                val navHost = rememberNavController()

                NavigationNavHost1(navHost)
                }
            }
        }
    }


