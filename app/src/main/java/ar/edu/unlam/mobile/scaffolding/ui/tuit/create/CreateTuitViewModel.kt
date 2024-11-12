package ar.edu.unlam.mobile.scaffolding.ui.tuit.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.CreateTuit
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTuitViewModel
    @Inject
    constructor(
        private val createTuitUseCase: CreateTuit,
    ) : ViewModel() {
        private val _uiState = mutableStateOf(CreateTuitState(createTuitState = UIState.None))
        val uiState: State<CreateTuitState> = _uiState

        fun createTuit(message: String) {
            viewModelScope.launch {
                _uiState.value =
                    _uiState
                        .value
                        .copy(
                            createTuitState = UIState.Loading,
                        )
                try {
                    val result = createTuitUseCase(message)
                    _uiState.value =
                        _uiState
                            .value
                            .copy(
                                createTuitState =
                                    if (result) {
                                        UIState.Success(Unit)
                                    } else {
                                        UIState.Error("Error al crear el tuit")
                                    },
                            )
                } catch (e: Exception) {
                    _uiState.value =
                        _uiState
                            .value
                            .copy(
                                createTuitState = UIState.Error(e.message ?: "Error desconocido"),
                            )
                }
            }
        }
    }
