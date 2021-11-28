package com.marvel.challenge.data.api.services

import com.marvel.challenge.data.api.response.characters.ServerCharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    companion object {

        //Headers
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val ACCEPT = "Accept: application/json"
    }


    @Headers(CONTENT_TYPE, ACCEPT)
    @GET("v1/public/characters")
    fun getCharactersList(
        @Query("limit") limit: Int, @Query("ts") ts: String, @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<ServerCharactersResponse>

    @Headers(CONTENT_TYPE, ACCEPT)
    @GET("v1/public/characters/{characterId}")
    fun getCharacterDescription(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<ServerCharactersResponse>

}
