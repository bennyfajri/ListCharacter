package com.drsync.listcharacter.ui.screen.favorited

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.repository.CharacterRepository
import com.drsync.listcharacter.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Character>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Character>>>
        get() = _uiState

    fun getFavoritedCharacter() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getFavoritedCharacter()
                .collect{ character ->
                    _uiState.value = UiState.Success(character)
                }
        }
    }
}