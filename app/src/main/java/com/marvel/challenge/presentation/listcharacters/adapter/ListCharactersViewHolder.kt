package com.marvel.challenge.presentation.listcharacters.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class ListCharactersViewHolder internal constructor(internal val view: View) : ViewHolder(view) {
    fun setCharacter(resultCharacterModel: ResultCharacterModel) {
        val urlImage =
            resultCharacterModel.thumbnail.path + "." + resultCharacterModel.thumbnail.extension
        Picasso.get().load(urlImage).into(view.ivCharacter)
        view.tvTitleCharacter.text = resultCharacterModel.name
    }
}
