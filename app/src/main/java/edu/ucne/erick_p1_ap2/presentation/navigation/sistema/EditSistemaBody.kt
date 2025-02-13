package edu.ucne.erick_p1_ap2.presentation.navigation.sistema

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.input.KeyboardType
import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.SistemaViewModel

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle







import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.UiState



@Composable
fun EditSistemaScreen(
    viewModel:  SistemaViewModel = hiltViewModel(),
    sistemaId: Int?,
    onDrawerToggle: () -> Unit,
    goTosistema: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = sistemaId) {
        if (sistemaId != null) {
            viewModel.get(sistemaId)
        }
    }

    BodyEditSistema(
        uiState = uiState,
        onNombreChange = viewModel::onNombreChange,
        onPrecioChange = viewModel::onPrecioChange,
        onDrawerToggle = onDrawerToggle,
        goTosistema = goTosistema,
        updatesistema = viewModel::update
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyEditSistema(
    uiState: UiState,
    onDrawerToggle: () -> Unit,
    onNombreChange: (String) -> Unit,
    onPrecioChange: (Double) -> Unit,
    goTosistema: () -> Unit,
    updatesistema: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar sistema") },
                navigationIcon = {
                    IconButton(onClick = {
                        onDrawerToggle()
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
        ) {

            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = onNombreChange,
                label = { Text("nombre") },
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

            Button(
                onClick = {
                    updatesistema()
                    goTosistema()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled =   uiState.nombre.isNotEmpty() &&  uiState.precio.toString().isNotEmpty()


            ) {
                Text("Actualizar")
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






