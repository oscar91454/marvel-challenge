package com.marvel.challenge.data

import com.marvel.challenge.data.api.response.characters.ServerCharactersResponse
import com.marvel.challenge.data.api.services.APIService
import com.marvel.challenge.data.extensions.executeCall
import com.marvel.challenge.data.mappers.toDomain
import com.marvel.challenge.domain.model.characters.ListCharactersModel
import com.marvel.challenge.domain.repository.DataRepository

class DataRepositoryImpl(private val apiService: APIService) : DataRepository {


    override fun getCharacterDetails(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): ListCharactersModel {
        val serverResponse = executeCall(
            apiService.getCharacterDescription(characterId = characterId, ts, apikey, hash)
        )
        val serverResponseBody = serverResponse.body() as ServerCharactersResponse
        return serverResponseBody.data.results.toDomain()
    }

    override fun getListCharacters(
        limit: Int,
        ts: String,
        apikey: String,
        hash: String
    ): ListCharactersModel {
        val serverResponse = executeCall(
            apiService.getCharactersList(
                limit = limit,
                ts,
                apikey,
                hash
            )
        )
        val serverResponseBody = serverResponse.body() as ServerCharactersResponse
        return serverResponseBody.data.results.toDomain()

    }

}
