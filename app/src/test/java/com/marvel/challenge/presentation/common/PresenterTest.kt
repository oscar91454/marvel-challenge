package com.marvel.challenge.presentation.common


import com.marvel.challenge.domain.interactors.common.Interactor
import com.marvel.challenge.domain.model.errors.ApiError
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

    // class under test
    private var sut = PresenterDummy()

    @Mock
    private lateinit var interactor: Interactor

    @Mock
    private lateinit var view: BaseView

    @Before
    fun setUp() {
        sut.attachView(view = view)
    }

    @Test
    fun onExecuteWithoutConnectionReturnErrorConnection() {
        val noConnectionCompleteCaptor = argumentCaptor<() -> Unit>()

        sut.execute(interactor = interactor, onSuccess = {})
        verify(interactor).invoke(
            onSuccess = any(),
            noConnection = noConnectionCompleteCaptor.capture(),
            onApiError = any(),
            genericError = any()
        )

        noConnectionCompleteCaptor.firstValue.invoke()
        verify(view).showConnectionError()
    }

    @Test
    fun onExecuteAndApiFailReturnApiError() {
        val apiErrorCompleteCaptor = argumentCaptor<(ApiError) -> Unit>()
        val errorMessage = "Message Error"
        val errorResult = "Result Error"
        val apiError = ApiError(errorResult, errorMessage)

        sut.execute(interactor = interactor, onSuccess = {})
        verify(interactor).invoke(
            onSuccess = any(),
            noConnection = any(),
            onApiError = apiErrorCompleteCaptor.capture(),
            genericError = any()
        )

        apiErrorCompleteCaptor.firstValue.invoke(apiError)
        verify(view).showError(errorMessage)
    }

    @Test
    fun onExecuteWithUnknownErrorReturnGenericError() {
        val genericCompleteCaptor = argumentCaptor<() -> Unit>()

        sut.execute(interactor = interactor, onSuccess = {})
        verify(interactor).invoke(
            onSuccess = any(),
            noConnection = any(), onApiError = any(), genericError = genericCompleteCaptor.capture()
        )

        genericCompleteCaptor.firstValue.invoke()
        verify(view).showDefaultError()
    }

    class PresenterDummy : Presenter<BaseView>() {
        override fun onViewAttached() {
        }
    }
}