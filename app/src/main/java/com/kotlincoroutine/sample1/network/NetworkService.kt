package com.kotlincoroutine.sample1.network

import com.kotlincoroutine.sample1.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val fetchDataService = retrofit.create(FetchDataService::class.java)

    suspend fun allAlbums(): List<Album> = withContext(Dispatchers.Default) {
    val result = fetchDataService.fetchAllAlbums()
        result.shuffled()
    }



}


interface FetchDataService {
    @GET("photos")
    suspend fun fetchAllAlbums(): List<Album>
}