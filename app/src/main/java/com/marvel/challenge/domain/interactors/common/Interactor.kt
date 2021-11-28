package com.marvel.challenge.domain.interactors.common


import com.marvel.challenge.data.exceptions.ApiException
import com.marvel.challenge.domain.extensions.cancelCoroutine
import com.marvel.challenge.domain.model.errors.ApiError
import com.marvel.challenge.domain.model.errors.GenericError
import com.marvel.challenge.domain.model.errors.NoConnectionError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.UnknownHostException

abstract class Interactor {
    abstract suspend fun execution(): Any
    operator fun invoke(
        onSuccess: (Any) -> Unit,
        noConnection: () -> Unit,
        onApiError: (ApiError) -> Unit,
        genericError: () -> Unit
    ) {
        val result = GlobalScope.async {
            try {
                execution()
            } catch (e: UnknownHostException) {
                NoConnectionError()
            } catch (e: ApiException) {
                ApiError(e.response, e.message ?: "")
            } catch (e: Exception) {
                GenericError()
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            when {
                result.await() is NoConnectionError -> noConnection()
                result.await() is ApiError -> onApiError((result.await() as ApiError))
                result.await() is GenericError -> genericError()
                else -> onSuccess(result.await())
            }
        }
    }

    fun cancel() = cancelCoroutine()
}