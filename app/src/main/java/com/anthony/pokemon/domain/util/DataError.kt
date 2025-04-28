package com.anthony.pokemon.domain.util

interface DataError: Error {
    enum class Network: DataError{
        UnknownException,
        SerializationException,
        InvalidRequestObject,
        TimeoutException
    }

    enum class Local: DataError{

    }

}