package com.marvel.challenge.domain.repository

import com.marvel.challenge.domain.model.characters.ListCharactersModel


interface DataRepository {

    fun getCharacterDetails(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): ListCharactersModel

    fun getListCharacters(limit: Int, ts: String, apikey: String, hash: String): ListCharactersModel
}
