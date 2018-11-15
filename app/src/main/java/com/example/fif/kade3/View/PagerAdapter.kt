package com.example.fif.kade3.View

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.fif.kade3.View.FragmentFavorite
import com.example.fif.kade3.View.FragmentLastMatch
import com.example.fif.kade3.View.FragmentNextMatch

class PagerAdapter(fragmenManager: FragmentManager) : FragmentPagerAdapter(fragmenManager) {
    override fun getItem(p0: Int): Fragment = when (p0) {
        0 -> FragmentLastMatch.newInstance()
        1 -> FragmentNextMatch.newInstance()
        2 -> FragmentFavorite.newInstance()
        else -> FragmentLastMatch.newInstance()
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Last Match"
        1 -> "Next Match"
        2 -> "Favorite"
        else -> "Last Match"
    }
}