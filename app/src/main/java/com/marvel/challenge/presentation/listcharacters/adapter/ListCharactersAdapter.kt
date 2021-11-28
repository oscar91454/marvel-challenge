package com.marvel.challenge.presentation.listcharacters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.challenge.R
import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import java.util.*

class ListCharactersAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<ListCharactersViewHolder>() {

    private var characters: MutableList<ResultCharacterModel> = ArrayList()

    fun setCharacters(characters: List<ResultCharacterModel>) {
        this.characters = characters.toMutableList()
    }

    fun addMoreCharacters(characters: List<ResultCharacterModel>) {
        this.characters.addAll(characters)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCharactersViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ListCharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListCharactersViewHolder, position: Int) {
        val character: ResultCharacterModel = characters[position]
        holder.setCharacter(character)
        holder.view.setOnClickListener { listener.RowClick(character) }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    interface ClickListener {
        fun RowClick(character: ResultCharacterModel)
    }
}
