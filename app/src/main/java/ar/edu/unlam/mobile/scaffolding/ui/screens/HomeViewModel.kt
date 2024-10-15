package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tuitRepository: TuitRepository,
) : ViewModel() {
    private val _tuits = MutableStateFlow<List<Tuit>>(emptyList())
    val tuits: StateFlow<List<Tuit>> = _tuits

    init {
        // Inicia la recolección de tuits desde el repositorio
        viewModelScope.launch {
            tuitRepository.getTuits().collect { fetchedTuits ->
                _tuits.value = fetchedTuits
            }
        }
    }
}
