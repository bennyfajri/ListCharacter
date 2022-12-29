package com.drsync.listcharacter.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.repository.CharacterRepository
import com.drsync.listcharacter.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Character>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Character>>>
        get() = _uiState

    fun getCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getCharacters()
                .collect { character ->
                    _uiState.value = UiState.Success(character)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchCharacters(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            _uiState.value = UiState.Loading
            repository.searchCharacters(query.value)
                .collect { character ->
                    _uiState.value = UiState.Success(character)
                }
        }
    }
}