package com.kotlincoroutine.sample1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlincoroutine.sample1.Album
import com.kotlincoroutine.sample1.R
import com.kotlincoroutine.sample1.adapter.AlbumAdapter

class MainActivity : AppCompatActivity(), AlbumAdapter.OnAlbumSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, AlbumListFragment.newInstance(), "albumList")
                .commit()
        }
    }

    override fun OnAlbumSelected(albumModel: Album) {
        val detailsFragment = AlbumDetailFragment.newInstance(albumModel)
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "albumDetail")
            .addToBackStack(null)
            .commit()
    }
}
