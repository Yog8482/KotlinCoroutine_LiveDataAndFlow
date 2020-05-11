package com.kotlincoroutine.sample1.ui

import androidx.lifecycle.*
import com.kotlincoroutine.sample1.Album
import com.kotlincoroutine.sample1.AlbumRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * The [ViewModel] for fetching a list of [Album]s.
 */
class AlbumListViewModel internal constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose [MutableLiveData].
     *
     * MutableLiveData allows anyone to set a value, and [AlbumListViewModel] is the only
     * class that should be setting values.
     * */


    private val _snackbar = MutableLiveData<String?>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _spinner = MutableLiveData<Boolean>(false)

    /**
     * Show Loading spinner if true
     */
    val  spinner: LiveData<Boolean>
        get() = _spinner

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackbar.value = null
    }

//    val albums: LiveData<List<Album>> = albumRepository.albums

    //Flow
    val albumsUsingFlow: LiveData<List<Album>> = albumRepository.albumsFlow.asLiveData()


    init {
        // initial code version, will move during flow rewrite
        launchDataLoad { albumRepository.tryUpdateRecentAlbumsCache() }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Throwable) {
                _snackbar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }
}