package com.marvel.challenge.presentation.common

interface BaseView {
    fun showLoading()

    fun hideLoading()

    fun showConnectionError()

    fun showDefaultError()

    fun showError(errorDescription: String)
}
