package com.example.fif.kade3.View

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fragmenManager: FragmentManager) : FragmentPagerAdapter(fragmenManager) {
    override fun getItem(p0: Int): Fragment = when (p0) {
        0 -> FragmentLastMatch.newInstance()
        1 -> FragmentNextMatch.newInstance()
        else -> FragmentLastMatch.newInstance()
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Last Match"
        1 -> "Next Match"
        else -> "Last Match"
    }
}