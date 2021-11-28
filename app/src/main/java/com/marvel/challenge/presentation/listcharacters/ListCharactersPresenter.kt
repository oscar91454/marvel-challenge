package com.marvel.challenge.presentation.listcharacters

import com.marvel.challenge.domain.interactors.GetListCharactersInteractor
import com.marvel.challenge.domain.model.characters.ListCharactersModel
import com.marvel.challenge.presentation.common.Presenter
import javax.inject.Inject

class ListCharactersPresenter
@Inject constructor(private val getListcharactersInteractor: GetListCharactersInteractor) :
    Presenter<ListCharactersView>() {

    var limit = 10

    override fun onViewAttached() {

    }


    fun getListCharacters(limit: Int, ts: String, apikey: String, hash: String) {
        view.showLoading()
        this.limit = limit
        getListcharactersInteractor.setParams(limit, ts, apikey, hash)
        execute(getListcharactersInteractor, onSuccess = this::successListCharacters)
    }

    private fun successListCharacters(data: Any) {
        val listCharacters = data as ListCharactersModel

        if (listCharacters.results.isNotEmpty()) {
            if (limit == 10) {
                view.fillListCharacters(listCharacters.results)
            } else {
                view.updateListCharacters(listCharacters.results)
            }
        } else {
            view.showDefaultError()
        }
        view.hideLoading()
    }

    override fun connectionError() {
        view.hideSwipeLoading()
        view.hideLoading()
        super.connectionError()
    }

}