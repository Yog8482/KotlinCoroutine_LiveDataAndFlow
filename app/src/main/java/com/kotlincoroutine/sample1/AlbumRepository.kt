package com.kotlincoroutine.sample1

import com.kotlincoroutine.sample1.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.intellij.lang.annotations.Flow

class AlbumRepository private constructor(
    private val albumdao: AlbumDao,
    private val albumService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    /**
     * Fetch a list of [Album]s from the database.
     * Returns a LiveData-wrapped List of Albums.
     */
//    val albums = albumdao.getAlbums()

    //Flow
    val albumsFlow: kotlinx.coroutines.flow.Flow<List<Album>>
        get() = albumdao.getAlbumsFlow()

    /**
     * Returns true if we should make a network request.
     */
    private fun shouldUpdateAlbumsCache(): Boolean {
        // suspending function, so you can e.g. check the status of the database here
        return true
    }


    /**
     * Update the albums cache.
     *
     * This function may decide to avoid making a network requests on every call based on a
     * cache-invalidation policy.
     */
    suspend fun tryUpdateRecentAlbumsCache() {
        if (shouldUpdateAlbumsCache()) fetchRecentAlbums()
    }

    /**
     * Fetch a new list of albums from the network, and append them to [albumDao]
     */
    private suspend fun fetchRecentAlbums() {
        val albums = albumService.allAlbums()
        albumdao.insertAll(albums)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(albumdao: AlbumDao, albumService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: AlbumRepository(albumdao, albumService).also { instance = it }
            }
    }
}