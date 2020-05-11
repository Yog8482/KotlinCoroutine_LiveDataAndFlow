package com.kotlincoroutine.sample1

import android.content.Context
import com.kotlincoroutine.sample1.network.FetchDataService
import com.kotlincoroutine.sample1.network.NetworkService
import com.kotlincoroutine.sample1.ui.AlbumListViewModelFactory


interface ViewModelFactoryProvider {

    fun provideAlbumListViewModelFactory(context: Context): AlbumListViewModelFactory
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

@Volatile
private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider

private object DefaultViewModelProvider : ViewModelFactoryProvider {
    private fun getAlbumRepository(context: Context): AlbumRepository {
        return AlbumRepository.getInstance(
            albumDao(context),
            albumService()
        )
    }

    override fun provideAlbumListViewModelFactory(context: Context): AlbumListViewModelFactory {
        val repository = getAlbumRepository(context)
        return AlbumListViewModelFactory(repository)
    }
}

private fun albumService() = NetworkService()
private fun albumDao(context: Context) =
    AppDatabase.getInstance(context.applicationContext).albumDao()