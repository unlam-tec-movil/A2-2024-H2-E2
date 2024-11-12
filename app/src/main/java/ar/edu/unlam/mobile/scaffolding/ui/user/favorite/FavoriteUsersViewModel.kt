package ar.edu.unlam.mobile.scaffolding.ui.user.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.GetFavoriteUsers
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.RemoveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.SaveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteUsersViewModel
    @Inject
    constructor(
        private val getFavoriteUsers: GetFavoriteUsers,
        private val removeFavoriteUser: RemoveFavoriteUser,
        private val saveFavoriteUser: SaveFavoriteUser,
    ) : ViewModel() {
        private val _state = MutableStateFlow(FavoriteUsersState())
        val state: StateFlow<FavoriteUsersState> = _state.asStateFlow()
        private var isRetrying = false

        init {
            loadFavoriteUsers()
        }

        fun loadFavoriteUsers() {
            viewModelScope.launch {
                _state.value = _state.value.copy(favoriteUserState = UIState.Loading)
                try {
                    val favUsers = getFavoriteUsers()
                    _state.value = _state.value.copy(favoriteUserState = UIState.Success(favUsers))
                    isRetrying = false
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            favoriteUserState = UIState.Error(e.message ?: "Error al cargar los usuarios favoritos"),
                        )
                }
            }
        }

        fun saveFavoriteUsers(user: FavoriteUser) {
            viewModelScope.launch {
                try {
                    saveFavoriteUser(user)
                    loadFavoriteUsers()
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            favoriteUserState = UIState.Error(e.message ?: "Error al agregar el usuario"),
                        )
                }
            }
        }

        fun removeFavoriteUsers(user: FavoriteUser) {
            viewModelScope.launch {
                try {
                    removeFavoriteUser(user)
                    getFavoriteUsers()
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            favoriteUserState = UIState.Error(e.message ?: "Error al eliminar usuario"),
                        )
                }
            }
        }

        fun retryLoadFavoriteUsers() {
            if (!isRetrying) {
                isRetrying = true
                loadFavoriteUsers()
            }
        }
    }
