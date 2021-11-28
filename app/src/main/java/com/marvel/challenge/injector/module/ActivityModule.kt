package com.marvel.challenge.injector.module

import com.marvel.challenge.presentation.characterdetails.CharacterDetailsActivity
import com.marvel.challenge.presentation.listcharacters.ListCharactersActivity
import com.marvel.challenge.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun bindListCharactersActivity(): ListCharactersActivity

    @ContributesAndroidInjector()
    abstract fun bindCharacterDetailsActivity(): CharacterDetailsActivity

}