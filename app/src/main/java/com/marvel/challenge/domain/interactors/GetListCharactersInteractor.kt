package com.marvel.challenge.domain.interactors


import com.marvel.challenge.domain.interactors.common.Interactor
import com.marvel.challenge.domain.repository.DataRepository
import javax.inject.Inject

class GetListCharactersInteractor
@Inject constructor(private val repository: DataRepository) :
    Interactor() {

    private var limit: Int = 0
    private lateinit var ts: String
    private lateinit var apikey: String
    private lateinit var hash: String

    fun setParams(limit: Int, ts: String, apikey: String, hash: String) {
        this.limit = limit
        this.ts = ts
        this.apikey = apikey
        this.hash = hash
    }

    override suspend fun execution() =
        repository.getListCharacters(limit, ts, apikey, hash)

}