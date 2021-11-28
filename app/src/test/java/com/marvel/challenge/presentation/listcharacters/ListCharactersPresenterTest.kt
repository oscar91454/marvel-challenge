package com.marvel.challenge.presentation.listcharacters

import com.marvel.challenge.domain.interactors.GetListCharactersInteractor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListCharactersPresenterTest {
    companion object {
        const val param1 = 10
        const val param2 = "ts"
        const val param3 = "apikey"
        const val param4 = "hash"
    }

    private lateinit var sut: ListCharactersPresenter

    @Mock
    private lateinit var getListCharactersInteractor: GetListCharactersInteractor

    @Mock
    private lateinit var view: ListCharactersView

    @Before
    fun setUp() {
        sut = ListCharactersPresenter(getListCharactersInteractor)
        sut.attachView(view)
    }

    @Test
    fun onExecuteGetListCharactersInteractorOnlyOnce() {
        sut.getListCharacters(param1, param2, param3, param4)
        getListCharactersInteractor.setParams(param1, param2, param3, param4)
        verify(getListCharactersInteractor, times(1)).invoke(any(), any(), any(), any())
    }

    @Test
    fun onExecuteGetListCharactersProgressDialog() {
        sut.getListCharacters(param1, param2, param3, param4)
        verify(view).showLoading()
    }

}