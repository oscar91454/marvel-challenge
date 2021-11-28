package com.marvel.challenge.presentation.characterdetails

import com.marvel.challenge.domain.interactors.GetCharacterDetailsInteractor
import com.marvel.challenge.domain.model.characters.ListCharactersModel
import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import com.marvel.challenge.presentation.common.Presenter
import javax.inject.Inject

class CharacterDetailsPresenter
@Inject constructor(private val getCharacterDetailsInteractor: GetCharacterDetailsInteractor) :
    Presenter<CharacterDetailsView>() {

    lateinit var resultCharacterModel: ResultCharacterModel

    override fun onViewAttached() {

    }

    fun getCharacterDetailsData(characterId: Int, ts: String, apikey: String, hash: String) {
        view.showLoading()
        getCharacterDetailsInteractor.setParams(characterId, ts, apikey, hash)
        execute(getCharacterDetailsInteractor, onSuccess = this::successGetCharacterDetails)

    }

    private fun successGetCharacterDetails(data: Any) {
        val listCharacters = data as ListCharactersModel

        if (listCharacters.results.isNotEmpty()) {
            view.fillCharactersDetails(listCharacters.results[0])
        } else {
            view.showDefaultError()
        }
        view.hideLoading()
    }

    override fun connectionError() {
        view.hideLoading()
        super.connectionError()
    }
}