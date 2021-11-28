package com.marvel.challenge.injector.module

import android.app.Application
import com.marvel.challenge.BuildConfig
import com.marvel.challenge.R
import com.marvel.challenge.data.api.services.APIService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("socketTimeout")
    fun provideSocketTimeout(context: Application): Int {
        return context.resources.getInteger(R.integer.socketTimeout)
    }

    @Provides
    @Singleton
    @Named("connectionTimeout")
    fun provideConnectionTimeout(context: Application): Int {
        return context.resources.getInteger(R.integer.connectionTimeout)
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BuildConfig.LEVEL_LOGS
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        context: Application,
        loggingInterceptor: HttpLoggingInterceptor,
        @Named("socketTimeout") socketTimeout: Int,
        @Named("connectionTimeout") connectionTimeout: Int
    ): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.readTimeout(socketTimeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.connectTimeout(connectionTimeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.addInterceptor(loggingInterceptor)

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}
