package com.inspiredcoda.dagger_hilt.di

import android.content.Context
import com.inspiredcoda.dagger_hilt.BuildConfig
import com.inspiredcoda.dagger_hilt.network.Api
import com.inspiredcoda.dagger_hilt.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNetworkInterceptor(
        @ApplicationContext context: Context
    ): NetworkInterceptor {
        return NetworkInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        networkInterceptor: NetworkInterceptor
    ): Retrofit{
        val client = OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api{
        return retrofit.create(Api::class.java)
    }

}