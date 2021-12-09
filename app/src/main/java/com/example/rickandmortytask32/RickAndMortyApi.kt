package com.example.rickandmortytask32

import com.example.rickandmortytask32.data.DataRickAndMortyNw
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RickAndMortyApi {
    @GET("character")
    fun getCharacterInfo(): Call<DataRickAndMortyNw>

    companion object {
        fun create(): RickAndMortyApi {
            Timber.d("GET CREATED!")
            val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            httpClient.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build()
            return retrofit.create(RickAndMortyApi::class.java)
        }
    }

}