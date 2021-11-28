package com.marvel.challenge.data.exceptions

class ApiException(var response: String, error: String) : Exception(error)
