package edu.ucne.erick_p1_ap2.presentation.navigation.sistema
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.SistemaViewModel
import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.UiState

@Composable
fun  CreateSistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    onDrawerToggle: () -> Unit,
    goToSistema: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BodyCreateSistema(
        uiState = uiState,
        onDrawerToggle = onDrawerToggle,
        onNombreChange = viewModel::onNombreChange,
        onPrecioChange = viewModel::onPrecioChange,
        goToSistema = goToSistema,
        save = viewModel::save
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyCreateSistema(
    uiState: UiState,
    onDrawerToggle: () -> Unit,
    onNombreChange: (String) -> Unit,
    onPrecioChange: (Double) -> Unit,
    goToSistema: () -> Unit,
    save: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Sistema") },
                navigationIcon = {
                    IconButton(onClick = onDrawerToggle) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = onNombreChange,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = if (uiState.precio == 0.0) "" else uiState.precio.toString(),
                onValueChange = { newValue ->
                    val parsedValue = newValue.toDoubleOrNull()
                    if (parsedValue != null) {
                        onPrecioChange(parsedValue)
                    } else if (newValue.isEmpty()) {
                        onPrecioChange(0.0)
                    }
                },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    save()
                    goToSistema()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
            uiState.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
