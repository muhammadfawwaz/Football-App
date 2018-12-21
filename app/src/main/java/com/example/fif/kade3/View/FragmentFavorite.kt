package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.R
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FragmentFavorite : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_favorite,container,false)
        view.apply {
            val adapter = FavoritePager(childFragmentManager)
            favoriteViewPager.adapter = adapter
            favoriteTabLayout.setupWithViewPager(favoriteViewPager)
        }
        return view
    }

    companion object {
        fun newInstance(): FragmentFavorite = FragmentFavorite()
    }
}