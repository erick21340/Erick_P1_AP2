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
    private val sistemaRepository: SistemaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getSistemas()
    }

    fun savesistema() {
        viewModelScope.launch {
            if (_uiState.value.nombre.isBlank()) {
                _uiState.update {
                    it.copy(errorMessage = "El nombre es obligatorio.", successMessage = null)
                }
                return@launch
            }

            try {
                sistemaRepository.save(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(successMessage = "Sistema guardado correctamente.", errorMessage = null)
                }
                nuevoSistema()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "Error al guardar el sistema: ${e.message}", successMessage = null)
                }
            }
        }
    }

    fun nuevoSistema() {
        _uiState.update {
            it.copy(
                sistemaId = null,
                nombre = "",
                errorMessage = null,
                successMessage = null
            )
        }
    }

    fun selectSistema(sistemaId: Int) {
        viewModelScope.launch {
            if (sistemaId > 0) {
                val sistema = sistemaRepository.find(sistemaId)
                _uiState.update {
                    it.copy(
                        sistemaId = sistema?.sistemaid,
                        nombre = sistema?.nombre ?: ""
                    )
                }
            }
        }
    }

    fun deleteSistema() {
        viewModelScope.launch {
            try {
                sistemaRepository.delete(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(successMessage = "Sistema eliminado correctamente.", errorMessage = null)
                }
                nuevoSistema()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "Error al eliminar el sistema: ${e.message}", successMessage = null)
                }
            }
        }
    }

    fun getSistemas() {
        viewModelScope.launch {
            sistemaRepository.getAll().collect { sistemas ->
                _uiState.update {
                    it.copy(sistemas = sistemas)
                }
            }
        }
    }

    fun onNombreChange(nombre: String) {
        _uiState.update {
            it.copy(nombre = nombre)
        }
    }

    fun clearMessages() {
        _uiState.update {
            it.copy(errorMessage = null, successMessage = null)
        }
    }

    data class UiState(
        val sistemaId: Int? = null,
        val nombre: String = "",
        val errorMessage: String? = null,
        val successMessage: String? = null,
        val sistemas: List<SistemaEntity> = emptyList()
    )

    fun UiState.toEntity() = SistemaEntity(
        sistemaid = sistemaId,
        nombre = nombre
    )
}
