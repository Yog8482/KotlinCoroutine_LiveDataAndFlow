package com.kotlincoroutine.sample1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM Album")
    fun getAlbums(): LiveData<List<Album>>

    @Query("SELECT * FROM Album")
    fun getAlbumsFlow(): Flow<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(album: List<Album>)
}