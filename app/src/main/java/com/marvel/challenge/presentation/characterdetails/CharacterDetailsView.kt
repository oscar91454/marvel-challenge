package com.marvel.challenge.presentation.characterdetails

import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import com.marvel.challenge.presentation.common.BaseView

interface CharacterDetailsView : BaseView {
    fun fillCharactersDetails(resultCharacterModel: ResultCharacterModel)
    fun getCharacterId(): Int
}