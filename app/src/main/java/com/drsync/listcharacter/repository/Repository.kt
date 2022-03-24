package com.drsync.listcharacter.repository

import androidx.compose.runtime.mutableStateOf
import com.drsync.listcharacter.api.RetrofitInstance
import com.drsync.listcharacter.data.Character

class Repository {

    private var _listCharacter = mutableStateOf<List<Character>>(listOf())
    var listCharacter = _listCharacter

    suspend fun getCharacter() {
        try {
            val response = RetrofitInstance.api.getCharacter()
            _listCharacter.value = response
        }catch (e: Exception) {
            return
        }
    }
}