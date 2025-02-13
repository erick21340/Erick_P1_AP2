package edu.ucne.erick_p1_ap2.presentation.screens.sistemas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.erick_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.erick_p1_ap2.data.local.repository.SistemaRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val SistemaRepository:SistemaRepository

) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getsistema()

    }

    fun validarCampos(): Boolean {
        return !_uiState.value.nombre.isNullOrBlank() &&
                _uiState.value.precio > 0.0
    }

    fun save() {
        viewModelScope.launch {
            if (!validarCampos()) {
                _uiState.update {
                    it.copy(
                        error = "Por favor, completa todos los campos correctamente.",
                        guardado = false
                    )
                }
                return@launch
            }

            try {
                val sistema = _uiState.value.toEntity()
                SistemaRepository.save(sistema)

                _uiState.update {
                    it.copy(
                        error = null,
                        guardado = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Ocurrió un error al guardar. Inténtalo nuevamente.",
                        guardado = false
                    )
                }
            }
        }
    }

    fun update() {
        viewModelScope.launch {
            if (!validarCampos() || _uiState.value.sistemaId == null) {
                _uiState.update {
                    it.copy(
                        error = "Por favor, completa todos los campos correctamente.",
                        guardado = false
                    )
                }
                return@launch
            }

            val sistemas = _uiState.value.toEntity()
            SistemaRepository.update(sistemas)
            _uiState.update { it.copy(error = null, guardado = true) }
        }
    }

    fun delete() {
        viewModelScope.launch {
            if (_uiState.value.sistemaId != null) {
                SistemaRepository.delete(_uiState.value.toEntity())
                _uiState.update { it.copy(sistemaId = null) }
            }
        }
    }

    fun get(sistemaId: Int) {
        viewModelScope.launch {

            println("Sistema recibido: $sistemaId")
            if (sistemaId > 0) {
                val sistema = SistemaRepository.get(sistemaId)
                _uiState.update { currentState ->
                    currentState.copy(
                        sistemaId = sistema?.sistemaId,
                        nombre = sistema?.nombre ?: "",
                        precio = sistema?.precio ?: 0.0,
                        error = null
                    )
                }
            }
        }
    }

    private fun getsistema() {
        viewModelScope.launch {
            SistemaRepository.getAll().collect { sistemas ->
                _uiState.update {
                    it.copy(sistemas = sistemas)
                }
            }
        }
    }

    fun onNombreChange(nombre: String) {
        _uiState.update {
            it.copy( nombre= nombre)
        }
    }

    fun onPrecioChange(precio: Double) {
        _uiState.update {
            it.copy(precio = precio)
        }
    }
}



data class UiState(
    val sistemaId: Int? = null,
    val nombre: String = "",
    val precio: Double = 0.0,
    val error: String? = null,
    var guardado: Boolean? = false,
    val sistemas: List<SistemaEntity> = emptyList()
)

fun UiState.toEntity() = SistemaEntity(
    sistemaId = sistemaId,
    nombre = nombre,
    precio = precio
)