package com.marvel.challenge.injector.component

import android.app.Application
import com.marvel.challenge.MainApplication
import com.marvel.challenge.injector.module.ActivityModule
import com.marvel.challenge.injector.module.AppModule
import com.marvel.challenge.injector.module.NetworkModule
import com.marvel.challenge.injector.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class, AppModule::class,
        NetworkModule::class, RepositoryModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MainApplication)
}
