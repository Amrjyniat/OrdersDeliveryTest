package com.example.ordersdeliverytest.di

import com.example.ordersdeliverytest.data.OrderApi
import com.example.ordersdeliverytest.util.Network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideSendibadyService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): OrderApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .baseUrl(Network.BASE_URL)
        .build()
        .create(OrderApi::class.java)

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val httpLogIntercept = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val interceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("Content-Type", "application/json")
            chain.proceed(builder.build())
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLogIntercept)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory()).build()

}