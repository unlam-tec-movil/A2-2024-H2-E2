package ar.edu.unlam.mobile.scaffolding.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed.GetFeed
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed.RefreshFeed
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
    @Inject
    constructor(
        private val getFeed: GetFeed,
        private val refreshFeed: RefreshFeed,
    ) : ViewModel() {

        private val _state = MutableStateFlow(FeedState())
        val state = _state.asStateFlow()

        init {
            loadFeed()
        }

        private fun loadFeed() {
            viewModelScope.launch {
                try {
                    getFeed().collect { tuits ->
                        _state.value = _state.value.copy(
                            tuitsState = UIState.Success(tuits)
                        )
                    }
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        tuitsState = UIState.Error(e.message ?: "Error al cargar los tuits")
                    )
                }
            }
        }

        fun onRefresh() {
            viewModelScope.launch {
                try {
                    _state.value = _state.value.copy(isRefreshing = true)
                    refreshFeed()
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        tuitsState = UIState.Error(e.message ?: "Error al actualizar los tuits")
                    )
                } finally {
                    _state.value = _state.value.copy(isRefreshing = false)
                }
            }
        }
    }
