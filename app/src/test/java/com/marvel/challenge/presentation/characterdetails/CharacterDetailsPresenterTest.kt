package com.marvel.challenge.presentation.characterdetails

import com.marvel.challenge.domain.interactors.GetCharacterDetailsInteractor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsPresenterTest {
    companion object {
        const val param1 = 10
        const val param2 = "ts"
        const val param3 = "apikey"
        const val param4 = "hash"
    }

    private lateinit var sut: CharacterDetailsPresenter

    @Mock
    private lateinit var getCharacterDetailsInteractor: GetCharacterDetailsInteractor

    @Mock
    private lateinit var view: CharacterDetailsView

    @Before
    fun setUp() {
        sut = CharacterDetailsPresenter(getCharacterDetailsInteractor)
        sut.attachView(view)
    }

    @Test
    fun onExecuteGetCharacterDetailsInteractorOnlyOnce() {
        sut.getCharacterDetailsData(param1, param2, param3, param4)
        getCharacterDetailsInteractor.setParams(param1, param2, param3, param4)
        verify(getCharacterDetailsInteractor, times(1)).invoke(any(), any(), any(), any())
    }

    @Test
    fun onExecuteGetCharacterDetailsProgressDialog() {
        sut.getCharacterDetailsData(param1, param2, param3, param4)
        verify(view).showLoading()
    }

}