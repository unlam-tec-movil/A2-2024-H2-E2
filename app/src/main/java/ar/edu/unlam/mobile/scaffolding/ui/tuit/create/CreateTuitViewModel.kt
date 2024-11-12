package ar.edu.unlam.mobile.scaffolding.ui.tuit.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.CreateTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.RemoveDraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.SaveDraftTuit
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTuitViewModel
    @Inject
    constructor(
        private val createTuitUseCase: CreateTuit,
        private val saveDraftTuitUseCase: SaveDraftTuit,
        private val removeDraftTuitUseCase: RemoveDraftTuit,
    ) : ViewModel() {
        private val _uiState = mutableStateOf(CreateTuitState(createTuitState = UIState.None))
        val uiState: State<CreateTuitState> = _uiState

        fun deleteDraftAfterPublish(message: String) {
            viewModelScope.launch {
                try {
                    removeDraftTuitUseCase(DraftTuit(message = message))
                } catch (_: Exception) {

                }
            }
        }

        fun createTuit(
            message: String,
            isFromDraft: Boolean = false
        ) {
            viewModelScope.launch {
                _uiState.value =
                    _uiState.value.copy(
                        createTuitState = UIState.Loading,
                    )
                try {
                    val result = createTuitUseCase(message)
                    if (result && isFromDraft) {
                            removeDraftTuitUseCase(DraftTuit(message = message))
                    }
                    _uiState.value =
                        _uiState.value.copy(
                            createTuitState =
                                if (result) {
                                    UIState.Success(Unit)
                                } else {
                                    UIState.Error("Error al crear el tuit")
                                },
                        )
                } catch (e: Exception) {
                    _uiState.value =
                        _uiState.value.copy(
                            createTuitState = UIState.Error(e.message ?: "Error desconocido"),
                        )
                }
            }
        }

        fun onCloseRequest(text: String) {
            if (text.isNotBlank()) {
                _uiState.value =
                    _uiState.value.copy(
                        showExitDialog = true,
                    )
            } else {
                _uiState.value =
                    _uiState.value.copy(
                        showExitDialog = false,
                    )
            }
        }

        fun dismissExitDialog() {
            _uiState.value =
                _uiState.value.copy(
                    showExitDialog = false,
                )
        }

        fun saveDraft(text: String) {
            viewModelScope.launch {
                _uiState.value =
                    _uiState.value.copy(
                        saveDraftState = UIState.Loading,
                    )
                try {
                    saveDraftTuitUseCase(
                        DraftTuit(
                            message = text,
                            lastModified = System.currentTimeMillis(),
                        ),
                    )
                    _uiState.value =
                        _uiState.value.copy(
                            saveDraftState = UIState.Success(Unit),
                            showExitDialog = false,
                        )
                } catch (e: Exception) {
                    _uiState.value =
                        _uiState.value.copy(
                            saveDraftState = UIState.Error(e.message ?: "Error al guardar borrador"),
                        )
                }
            }
        }
    }
