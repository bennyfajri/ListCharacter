package com.drsync.listcharacter.data

import java.io.Serializable

data class Character(
    val id: Int,
    val name: String,
    val sex: String,
    val birthday: String,
    val region: String,
    val lore: String,
    val characterImageId: Int = 0
) : Serializable