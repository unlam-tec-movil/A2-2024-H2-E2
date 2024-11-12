package ar.edu.unlam.mobile.scaffolding.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed.GetFeed
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed.RefreshFeed
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.GetFavoriteUsers
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.RemoveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.SaveFavoriteUser
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
        private var tuitRepository: TuitRepository,
        private val saveFavoriteUser: SaveFavoriteUser,
        private val removeFavoriteUser: RemoveFavoriteUser,
        private val getFavoriteUsers: GetFavoriteUsers,
    ) : ViewModel() {
        private val _state = MutableStateFlow(FeedState())
        val state = _state.asStateFlow()

        private val favoriteUsers = mutableSetOf<FavoriteUser>()

        init {
            loadFeed()
            loadFavoriteUsers()
        }

        private fun loadFeed() {
            viewModelScope.launch {
                try {
                    getFeed().collect { tuits ->
                        _state.value =
                            _state.value.copy(
                                tuitsState = UIState.Success(tuits),
                            )
                    }
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            tuitsState =
                                UIState.Error(
                                    e.message ?: "Error al cargar los tuits",
                                ),
                        )
                }
            }
        }

        private fun loadFavoriteUsers() {
            viewModelScope.launch {
                try {
                    val favoriteUsersList = getFavoriteUsers()
                    favoriteUsers.clear()
                    favoriteUsers.addAll(favoriteUsersList)
                    _state.value =
                        _state.value.copy(
                            favoriteUsers = favoriteUsers.toSet(),
                        )
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            tuitsState = UIState.Error(e.message ?: "Error al cargar los usuarios favoritos"),
                        )
                }
            }
        }

        fun onRefresh() {
            viewModelScope.launch {
                _state.value = _state.value.copy(isRefreshing = true)
                try {
                    refreshFeed().collect { tuits ->
                        _state.value = _state.value.copy(tuitsState = UIState.Success(tuits))
                    }
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            tuitsState =
                                UIState.Error(
                                    e.message ?: "Error al actualizar los tuits",
                                ),
                        )
                } finally {
                    _state.value =
                        _state.value.copy(
                            isRefreshing = false,
                        )
                }
            }
        }

        fun toggleTuitLike(
            tuitId: Int,
            isLiked: Boolean,
        ) {
            if (isLiked) {
                unlikeTuit(tuitId)
            } else {
                likeTuit(tuitId)
            }
        }

        private fun likeTuit(id: Int) {
            viewModelScope.launch {
                tuitRepository.likeTuit(tuitId = id)
                // Completar y agregar caso de error
            }
        }

        private fun unlikeTuit(id: Int) {
            viewModelScope.launch {
                tuitRepository.unlikeTuit(tuitId = id)
                // Completar y agregar caso de error
            }
        }

        fun onFavoriteClick(favoriteUser: FavoriteUser) {
            viewModelScope.launch {
                val isFavorite = favoriteUsers.contains(favoriteUser)
                if (isFavorite) {
                    removeFavoriteUser(favoriteUser)
                    favoriteUsers.remove(favoriteUser)
                } else {
                    saveFavoriteUser(favoriteUser)
                    favoriteUsers.add(favoriteUser)
                }
                _state.value = _state.value.copy(favoriteUsers = favoriteUsers.toSet())
            }
        }
    }
