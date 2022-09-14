package com.example.fiveletters.di

import com.example.fiveletters.BuildConfig
import com.example.fiveletters.data.api.WordsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideConvertFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideClient(
        apiKeyInterceptor: Interceptor
    ) = OkHttpClient.Builder()
        //.addInterceptor(apiKeyInterceptor)
        .also {
            it.addInterceptor(
                HttpLoggingInterceptor()
            )
        }
        .cache(null)
        .build()

    @Provides
    @Singleton
    fun provideWordsApi(retrofit: Retrofit): WordsApi = retrofit
        .create(WordsApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okhttp: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttp)
        .addConverterFactory(converterFactory)
        .build()

    companion object {
        // private const val BASE_URL = "http://api.wordnik.com/v4"
        private const val BASE_URL = "https://random-word-api.herokuapp.com"
        private const val API_KEY = BuildConfig.API_KEY
        private const val QUERY_API_KEY = "apikey"
    }
}
