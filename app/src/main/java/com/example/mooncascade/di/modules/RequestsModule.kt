package com.example.mooncascade.di.modules

import com.example.mooncascade.data.RetrofitCall
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RequestsModule {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor

        @Provides
        @Singleton
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    internal val rxJava2CallAdapterFactory: RxJava2CallAdapterFactory

        @Provides
        @Singleton
        get() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun getcurrentweather(retrofit: Retrofit): RetrofitCall {
        return retrofit.create<RetrofitCall>(RetrofitCall::class.java)
    }

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    companion object {
        const val BASE_URL = "https://weather.aw.ee"
    }

}