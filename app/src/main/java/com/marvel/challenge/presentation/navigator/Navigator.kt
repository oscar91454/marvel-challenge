package com.marvel.challenge.presentation.navigator

import android.content.Context
import android.content.Intent
import com.marvel.challenge.presentation.characterdetails.CharacterDetailsActivity
import com.marvel.challenge.presentation.common.BaseActivity
import com.marvel.challenge.presentation.listcharacters.ListCharactersActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    interface From {
        companion object {
            const val NOT_FOLLOW = -1
        }
    }


    private fun navigate(context: Context, requestCode: Int, intent: Intent?) {
        (context as BaseActivity).startActivityForResult(intent, requestCode)
    }

    fun navigateToListCharacters(context: Context) {
        val iDetail = ListCharactersActivity.buildIntent(context)
        navigate(context, From.NOT_FOLLOW, iDetail)
    }

    fun navigateToCharacterDetails(context: Context, characterId: Int) {
        navigate(
            context,
            From.NOT_FOLLOW,
            CharacterDetailsActivity.buildIntent(context, characterId)
        )
    }

}
