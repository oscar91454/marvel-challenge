package com.marvel.challenge.data.extensions

import retrofit2.Call
import retrofit2.Response
import java.io.IOException


@Throws(Exception::class)
fun executeCall(call: Call<*>): Response<*> {
    val response = call.execute()
    if (!response.isSuccessful) {
        throw IOException()
    }
    return response
}
