package com.anthony.pokemon

import android.app.Application
import com.anthony.pokemon.di.pokemonModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokemonApp)
            modules(pokemonModules)
        }
    }
}