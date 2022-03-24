package com.drsync.listcharacter.api

import com.drsync.listcharacter.data.Character
import retrofit2.http.GET

interface Api {
    @GET("get_character.php")
    suspend fun getCharacter(): List<Character>
}