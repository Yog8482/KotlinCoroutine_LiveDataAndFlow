package com.kotlincoroutine.sample1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlincoroutine.sample1.Album

import com.kotlincoroutine.sample1.R
import com.kotlincoroutine.sample1.databinding.FragmentAlbumDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val model = arguments!!.getSerializable(ALBUMMODEL) as Album
        binding.albumModel = model
        return binding.root

    }

    companion object {
        private const val ALBUMMODEL = "model"

        @JvmStatic
        fun newInstance(albumModel: Album) =
            AlbumDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ALBUMMODEL, albumModel)
                }
            }
    }
}
