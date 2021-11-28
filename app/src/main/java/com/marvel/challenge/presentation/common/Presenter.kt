package com.marvel.challenge.presentation.common

import com.marvel.challenge.domain.extensions.cancelCoroutine
import com.marvel.challenge.domain.interactors.common.Interactor
import com.marvel.challenge.domain.model.errors.ApiError

abstract class Presenter<V : BaseView> {
    protected lateinit var view: V

    fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    fun detachView() = cancelCoroutine()

    abstract fun onViewAttached()

    fun execute(
        interactor: Interactor,
        onSuccess: (Any) -> Unit,
        noConnection: () -> Unit = this::connectionError,
        onApiError: (ApiError) -> Unit = this::onApiError,
        genericError: () -> Unit = this::genericError
    ) {
        interactor.invoke(onSuccess, noConnection, onApiError, genericError)
    }

    open fun connectionError() {
        view.hideLoading()
        view.showConnectionError()
    }

    private fun onApiError(apiError: ApiError) {
        view.hideLoading()
        view.showError(apiError.message)
    }

    private fun genericError() {
        view.hideLoading()
        view.showDefaultError()
    }
}