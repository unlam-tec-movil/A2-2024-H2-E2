package ar.edu.unlam.mobile.scaffolding.ui.tuit.draft

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.GetDraftTuits
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DraftTuitViewModel
    @Inject
    constructor(
        private val getDraftTuitsUseCase: GetDraftTuits,
    ) : ViewModel() {
        private val _state = MutableStateFlow(DraftTuitState())
        val state = _state.asStateFlow()

        init {
            loadDraftTuitFeed()
        }

        private fun loadDraftTuitFeed() {
            viewModelScope.launch {
                try {
                    getDraftTuitsUseCase().collect { drafts ->
                        _state.value =
                            _state.value.copy(
                                draftTuitState = UIState.Success(drafts),
                            )
                    }
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            draftTuitState =
                                UIState.Error(
                                    e.message ?: "Error al cargar los drafts",
                                ),
                        )
                }
            }
        }
    }
