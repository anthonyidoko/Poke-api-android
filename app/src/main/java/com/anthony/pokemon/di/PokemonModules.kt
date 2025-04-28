package com.anthony.pokemon.di

import com.anthony.pokemon.data.RepositoryImpl
import com.anthony.pokemon.data.datasource.KtorDataSource
import com.anthony.pokemon.data.service.PokemonService
import com.anthony.pokemon.domain.repository.PokemonRepository
import com.anthony.pokemon.domain.usecases.GetPokemonDetailUseCase
import com.anthony.pokemon.domain.usecases.GetPokemonListUseCase
import com.anthony.pokemon.presentation.PokemonSharedViewModel
import com.anthony.pokemon.presentation.pokemondetails.PokemonDetailViewModel
import com.anthony.pokemon.presentation.pokemonlist.PokemonListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.CallRequestExceptionHandler
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pokemonModules = module {
    single { HttpClient(OkHttp){

        install(Logging){
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })

        }
        expectSuccess = true
        HttpResponseValidator {
            handleResponseException(object : CallRequestExceptionHandler {
                override suspend fun invoke(cause: Throwable, request: HttpRequest) {
                    println(cause)
                }
            })
        }
        engine {

        }
    }
    }
    singleOf(::GetPokemonListUseCase)
    singleOf(::GetPokemonDetailUseCase)
    singleOf(::KtorDataSource) bind PokemonService::class
    singleOf(::RepositoryImpl) bind PokemonRepository::class
    viewModelOf(::PokemonListViewModel)
    viewModelOf(::PokemonDetailViewModel)
    viewModelOf(::PokemonSharedViewModel)

}