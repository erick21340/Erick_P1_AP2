package edu.ucne.erick_p1_ap2.presentation.navigation.sistema


import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.SistemaViewModel



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment


import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle





import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity


@Composable
fun IndexSistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    onDrawerToggle: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BodySistema(
        uiState = uiState,
        onDrawerToggle = onDrawerToggle,
        onNombreChange = viewModel::onNombreChange,
        saveSistema = viewModel::savesistema
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodySistema(
    uiState: SistemaViewModel.UiState,
    onDrawerToggle: () -> Unit,
    onNombreChange: (String) -> Unit,
    saveSistema: () -> Unit,
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sistema") },
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
                .padding(8.dp)
        ) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Nombre") },
                        value = uiState.nombre,
                        onValueChange = onNombreChange,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    errorMessage?.let {
                        Text(text = it, color = Color.Red)
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(onClick = {
                            onNombreChange("") // Limpiar el nombre
                        }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Nuevo")
                            Text(text = "Nuevo")
                        }

                        OutlinedButton(
                            onClick = {
                                if (uiState.nombre.isBlank()) {
                                    errorMessage = "Nombre vacío"
                                } else {
                                    errorMessage = null
                                    saveSistema()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Guardar")
                            Text(text = "Guardar")
                        }
                    }
                }
            }

            SistemaListScreen(sistemaList = uiState.sistemas)
        }
    }
}
@Composable
fun SistemaListScreen(sistemaList: List<SistemaEntity>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Lista de Sistemas", style = MaterialTheme.typography.headlineSmall)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sistemaList) { sistema ->
                SistemaRow(sistema)
            }
        }
    }
}

@Composable
private fun SistemaRow(sistema: SistemaEntity) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = sistema.sistemaid?.toString() ?: "",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(2f),
            text = sistema.nombre,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Divider()
}

