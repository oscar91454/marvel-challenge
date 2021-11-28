package com.marvel.challenge.injector.module

import android.content.Context
import com.marvel.challenge.R
import com.marvel.challenge.presentation.widget.spinner.SpinnerLoading
import com.marvel.challenge.presentation.widget.spinner.SpinnerLoadingImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val nameApp = "Marvel Challenge"
    }

    @Provides
    @Singleton
    @Named(nameApp)
    fun provideNameApp(context: Context): String {
        return context.getString(R.string.app_name)
    }

    @Provides
    fun provideSpinnerLoading(): SpinnerLoading {
        return SpinnerLoadingImpl()
    }

}
