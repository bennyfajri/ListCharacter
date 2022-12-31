package com.drsync.listcharacter.data.room

import androidx.room.*
import com.drsync.listcharacter.data.entity.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters ORDER BY id")
    fun getCharacter(): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE favorited = 1")
    fun getFavoritedCharacter(): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%' OR region LIKE '%' || :query || '%' ")
    fun searchCharacters(query : String): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): Character

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<Character>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(chracter: Character)

    @Update
    suspend fun updateCharacter(chracter: Character)

    @Query("DELETE FROM characters WHERE favorited = 0")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM characters WHERE name = :name AND favorited = 1)")
    suspend fun isCharacterFavorited(name: String): Boolean
}