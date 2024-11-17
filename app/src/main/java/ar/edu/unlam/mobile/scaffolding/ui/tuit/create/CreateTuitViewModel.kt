package ar.edu.unlam.mobile.scaffolding.ui.tuit.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
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
        private val profileRepository: ProfileRepository,
    ) : ViewModel() {
        private val _uiState = mutableStateOf(CreateTuitState(createTuitState = UIState.None))
        val uiState: State<CreateTuitState> = _uiState

        fun deleteDraftById(draftId: Int) {
            viewModelScope.launch {
                _uiState.value =
                    _uiState.value.copy(
                        deleteDraftState = UIState.Loading,
                    )
                try {
                    removeDraftTuitUseCase(draftId)
                    _uiState.value =
                        _uiState.value.copy(
                            deleteDraftState = UIState.Success(Unit),
                        )
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(
                        deleteDraftState = UIState.Error("Error al eliminar el borrador: ${e.message}"),
                    )
                }
            }
        }

        fun createTuit(
            message: String,
            draftId: Int? = null,
        ) {
            viewModelScope.launch {
                _uiState.value =
                    _uiState.value.copy(
                        createTuitState = UIState.Loading,
                    )
                try {
                    val result = createTuitUseCase(message)
                    if (result && draftId != null) {
                        deleteDraftById(draftId)
                    }
                    _uiState.value =
                        _uiState.value.copy(
                            createTuitState = if (result) UIState.Success(Unit) else UIState.Error("Error al crear el tuit")
                        )
                    } catch (e: Exception) {
                        _uiState.value =
                            _uiState.value.copy(
                                createTuitState = UIState.Error(e.message ?: "Error desconocido"),
                            )
                    }
            }
        }

        fun onCloseRequest(text: String, draftId: Int?) {
            if (text.isNotBlank()) {
                _uiState.value =
                    _uiState.value.copy(
                        showExitDialog = true,
                    )
            } else if (draftId != null) {
                deleteDraftById(draftId)
                dismissExitDialog()
            } else {
                dismissExitDialog()
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
                    val userEmail = profileRepository.getProfile().email
                    saveDraftTuitUseCase(
                        DraftTuit(
                            message = text,
                            lastModified = System.currentTimeMillis(),
                            userEmail = userEmail,
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
