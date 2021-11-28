package com.marvel.challenge.domain.extensions

import kotlinx.coroutines.*

// This method helps to cover our coroutines, that they will be launched always from the main thread (Dispatchers.Main)
fun launch(block: suspend CoroutineScope.() -> Unit): Job =
    CoroutineScope(Dispatchers.Main).launch { block() }

// This method creates a new coroutine to launch the function that receives by parameter, and returns
// something like a future value (Deferred). When we need obtain the result, we will use await() function to indicate
// we want to wait for the response
fun <T> async(function: () -> T): Deferred<T> =
    CoroutineScope(Dispatchers.IO).async { function() }

// This method launch the function that receives by parameter in another thread and wait each response
// (it doesn't create anothe coroutine, only changes the execution thread) sequencial or depending ws calls
suspend fun <T> asyncSeq(block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO) { block() }

// Cancel a coroutine
fun cancelCoroutine(): Unit =
    CoroutineScope(Dispatchers.Main).cancel()
