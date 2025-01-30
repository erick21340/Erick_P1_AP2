package edu.ucne.erick_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.ucne.erick_p1_ap2.presentation.navigation.ParcialNavHost
import edu.ucne.erick_p1_ap2.ui.theme.Erick_P1_AP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Erick_P1_AP2Theme {
                val navHost = rememberNavController()

                    ParcialNavHost(navHost)
                }
            }
        }
    }


