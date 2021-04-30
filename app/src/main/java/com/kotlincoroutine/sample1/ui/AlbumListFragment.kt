package com.kotlincoroutine.sample1.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.kotlincoroutine.sample1.AlbumRepository
import com.kotlincoroutine.sample1.Injector
import com.kotlincoroutine.sample1.adapter.AlbumAdapter
import com.kotlincoroutine.sample1.databinding.AlbumListFragmentBinding

class AlbumListFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumListFragment()
    }

    private val viewModel: AlbumListViewModel by viewModels {
        Injector.provideAlbumListViewModelFactory(
            requireContext()
        )
    }
    private lateinit var listener: AlbumAdapter.OnAlbumSelected

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AlbumAdapter.OnAlbumSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnAlbumSelected.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AlbumListFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        // show the spinner when [spinner] is true
        viewModel.spinner.observe(viewLifecycleOwner) { show ->
            binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
        }

        // Show a snackbar whenever the [snackbar] is updated a non-null value
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }

        val adapter = AlbumAdapter(listener)
        binding.albumListRecyclerview.adapter = adapter
        subscribeUi(adapter)

        return binding.root

    }

    private fun subscribeUi(adapter: AlbumAdapter) {
        viewModel.albumsUsingFlow.observe(viewLifecycleOwner) { albums ->
            adapter.submitList(albums)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }



}

/**
 * Factory for creating a [AlbumListViewModel] with a constructor that takes a [AlbumRepository].
 */
class AlbumListViewModelFactory(
    private val repository: AlbumRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumListViewModel(repository) as T
}


