package com.marvel.challenge

import android.app.Application
import com.marvel.challenge.injector.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initializeComponentsHelper()
    }

    private fun initializeComponentsHelper() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    // this is required to setup Dagger2 for Activity
    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
