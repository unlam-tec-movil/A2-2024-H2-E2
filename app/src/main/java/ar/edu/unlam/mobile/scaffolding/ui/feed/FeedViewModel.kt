package ar.edu.unlam.mobile.scaffolding.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed.GetFeed
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed.RefreshFeed
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.interaction.LikeTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.interaction.UnLikeTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.GetFavoriteUsers
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.RemoveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.SaveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.getSuccessData
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
        private val saveFavoriteUser: SaveFavoriteUser,
        private val removeFavoriteUser: RemoveFavoriteUser,
        private val getFavoriteUsers: GetFavoriteUsers,
        private val saveLikeTuit: LikeTuit,
        private val saveUnLikeTuit: UnLikeTuit,
        private val profileRepository: ProfileRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(FeedState())
        private val favoriteUsers = mutableSetOf<FavoriteUser>()
        private var currentPage = 1
        var isLoadingMoreTuits = false
        val state = _state.asStateFlow()

        init {
            loadFeed()
            loadFavoriteUsers()
        }

        private fun loadFeed(page: Int = 1) {
            viewModelScope.launch {
                try {
                    getFeed(page).collect { tuits ->
                        _state.value =
                            _state
                                .value
                                .copy(
                                    tuitsState =
                                        UIState
                                            .Success(
                                                state.value.tuitsState
                                                    .getSuccessData()
                                                    ?.plus(tuits) ?: tuits,
                                            ),
                                )
                        isLoadingMoreTuits = false
                    }
                } catch (e: Exception) {
                    _state.value =
                        _state
                            .value
                            .copy(
                                tuitsState =
                                    UIState
                                        .Error(e.message ?: "Error al cargar los tuits"),
                            )
                    isLoadingMoreTuits = false
                }
            }
        }

        fun loadMoreFeed() {
            currentPage++
            isLoadingMoreTuits = true
            loadFeed(currentPage)
        }

        private fun loadFavoriteUsers() {
            viewModelScope.launch {
                try {
                    getFavoriteUsers().collect { favoriteUsersList ->
                        favoriteUsers.clear()
                        favoriteUsers.addAll(favoriteUsersList)
                        _state.value =
                            _state.value.copy(
                                favoriteUsers = favoriteUsers.toSet(),
                            )
                    }
                } catch (e: Exception) {
                    _state.value =
                        _state
                            .value
                            .copy(
                                tuitsState =
                                    UIState.Error(
                                        e.message ?: "Error al cargar los usuarios favoritos",
                                    ),
                            )
                }
            }
        }

        fun onRefresh() {
            viewModelScope.launch {
                _state.value =
                    _state
                        .value
                        .copy(isRefreshing = true)
                try {
                    refreshFeed.invoke().collect { firstPageTuits ->
                        _state.value =
                            _state.value.copy(
                                tuitsState = UIState.Success(firstPageTuits),
                            )
                    }
                } catch (e: Exception) {
                    _state.value =
                        _state
                            .value
                            .copy(
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
            isNotLiked: Boolean,
        ) {
            if (isNotLiked) {
                likeTuit(tuitId)
            } else {
                unlikeTuit(tuitId)
            }
        }

        private fun likeTuit(tuitId: Int) {
            viewModelScope.launch {
                try {
                    saveLikeTuit(tuitId)
                    val updatedTuits = updateTuitsAfterLike(tuitId, true)
                    _state.value =
                        _state.value.copy(
                            tuitsState = UIState.Success(updatedTuits),
                        )
                } catch (e: Exception) {
                    _state.value =
                        _state
                            .value
                            .copy(
                                tuitsState = UIState.Error(e.message ?: "Error al dar like al tuit"),
                            )
                }
            }
        }

        private fun unlikeTuit(tuitId: Int) {
            viewModelScope.launch {
                try {
                    saveUnLikeTuit(tuitId)
                    val updatedTuits = updateTuitsAfterLike(tuitId, false)
                    _state.value =
                        _state.value.copy(
                            tuitsState = UIState.Success(updatedTuits),
                        )
                } catch (e: Exception) {
                    _state.value =
                        _state
                            .value
                            .copy(
                                tuitsState =
                                    UIState.Error(
                                        e.message ?: "Error al quitar el like del tuit",
                                    ),
                            )
                }
            }
        }

        private fun updateTuitsAfterLike(
            tuitId: Int,
            isLiked: Boolean,
        ): List<Tuit> {
            val currentTuits = state.value.tuitsState.getSuccessData() ?: emptyList()
            val updatedTuits =
                currentTuits
                    .map { tuit ->
                        if (tuit.id == tuitId) {
                            tuit.copy(liked = isLiked)
                        } else {
                            tuit
                        }
                    }
            return updatedTuits
        }

        fun onFavoriteClick(favoriteUser: FavoriteUser) {
            viewModelScope.launch {
                val userEmail = profileRepository.getProfile().email
                if (isFavorite(favoriteUser)) {
                    removeFavoriteUser(favoriteUser.copy(userEmail = userEmail))
                    favoriteUsers.remove(favoriteUser)
                } else {
                    saveFavoriteUser(favoriteUser.copy(userEmail = userEmail))
                    favoriteUsers.add(favoriteUser)
                }
                _state.value =
                    _state.value.copy(
                        favoriteUsers = favoriteUsers.toSet(),
                    )
            }
        }

        private fun isFavorite(favoriteUser: FavoriteUser): Boolean =
            state.value.favoriteUsers.any {
                it.name == favoriteUser.name && it.avatarUrl == favoriteUser.avatarUrl
            }
    }
