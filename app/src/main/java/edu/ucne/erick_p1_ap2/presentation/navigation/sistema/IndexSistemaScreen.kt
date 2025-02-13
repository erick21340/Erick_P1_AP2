package edu.ucne.erick_p1_ap2.presentation.navigation.sistema


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.erick_p1_ap2.presentation.screens.sistemas.SistemaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndexSistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    onDrawerToggle: () -> Unit,
    onCreateSistema: () -> Unit,
    onEditSistema: (Int) -> Unit,
    onDeleteSistema: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sistema") },
                navigationIcon = {
                    IconButton(onClick = onDrawerToggle) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateSistema,
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
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
            if (uiState.sistemas.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "sistema",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No se ha encontrado ningún registro de sistema",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            } else {
                LazyColumn {
                    items(uiState.sistemas) { sistema ->
                        SistemaCard(
                            sistema = sistema,
                            onEditSistema = onEditSistema,
                            onDeleteSistema = onDeleteSistema
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SistemaCard(
    sistema: SistemaEntity,
    onEditSistema: (Int) -> Unit,
    onDeleteSistema: (Int) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onEditSistema(sistema.sistemaId ?: 0) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Asunto: ${sistema.nombre} (${sistema.nombre})", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "precio: ${sistema.precio}")
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onEditSistema(sistema.sistemaId ?: 0) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { onDeleteSistema(sistema.sistemaId ?: 0) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}