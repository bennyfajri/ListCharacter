package com.drsync.listcharacter.repository

import android.util.Log
import com.drsync.listcharacter.data.DataProvider
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.data.room.CharacterDao
import com.drsync.listcharacter.util.Constants.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class CharacterRepository private constructor(
    private val characterDao: CharacterDao
) {

    fun getCharacters(): Flow<List<Character>> = flow {
        try {
            val characters = DataProvider.characterList
            characterDao.deleteAll()
            characterDao.insertCharacters(characters)
        } catch (e: Exception) {
            Log.d(TAG, "getCharacters: ${e.message.toString()} ")
            emit(listOf())
        }
        val localData: Flow<List<Character>> =
            characterDao.getCharacter()
        emitAll(localData)
    }

    fun getFavoritedCharacter(): Flow<List<Character>> {
        return characterDao.getFavoritedCharacter()
    }

    suspend fun setFavoritedCharacter(character: Character, favoriteState: Boolean) {
        character.isFavorited = favoriteState
        characterDao.updateCharacter(character)
    }

    suspend fun insertCharacter(character: Character) {
        characterDao.insertCharacter(character)
    }

    companion object {
        @Volatile
        private var instance: CharacterRepository? = null
        fun getInstance(
            characterDao: CharacterDao
        ): CharacterRepository =
            instance ?: synchronized(this) {
                instance ?: CharacterRepository(characterDao)
            }.also { instance = it }
    }
}