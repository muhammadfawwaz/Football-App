package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FavoritePager(fragmenManager: FragmentManager) : FragmentPagerAdapter(fragmenManager) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> FragmentFavoriteMatch.newInstance()
            1 -> FragmentFavoriteTeam.newInstance()
            else -> FragmentFavoriteMatch.newInstance()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Matchs"
        1 -> "Teams"
        else -> "Matchs"
    }
}