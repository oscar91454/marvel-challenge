package com.marvel.challenge.presentation.listcharacters

import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import com.marvel.challenge.presentation.common.BaseView

interface ListCharactersView : BaseView {
    fun fillListCharacters(listCharacters: List<ResultCharacterModel>)
    fun updateListCharacters(listCharacters: List<ResultCharacterModel>)
    fun hideSwipeLoading()
}