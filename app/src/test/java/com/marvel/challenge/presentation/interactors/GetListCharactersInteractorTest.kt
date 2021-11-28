package com.marvel.challenge.presentation.interactors

import com.marvel.challenge.domain.interactors.GetListCharactersInteractor
import com.marvel.challenge.domain.repository.DataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetListCharactersInteractorTest {

    lateinit var sut: GetListCharactersInteractor

    @Mock
    lateinit var dataRepository: DataRepository


    @Before
    fun setup() {
        initInteractor()
    }

    companion object {
        const val param1 = 10
        const val param2 = "ts"
        const val param3 = "apikey"
        const val param4 = "hash"
    }

    private fun initInteractor() {
        sut = GetListCharactersInteractor(dataRepository)
        sut.setParams(param1, param2, param3, param4)
    }

    @Test
    @Throws(Exception::class)
    fun testGetListCharactersCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execution() }
        GetListCharactersInteractor(dataRepository)
    }


}
