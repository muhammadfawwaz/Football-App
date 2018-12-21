package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TeamPager(fragmenManager: FragmentManager,private val bundle: Bundle) : FragmentPagerAdapter(fragmenManager) {
    override fun getItem(p0: Int): Fragment {
        var x = FragmentTeamDesc.newInstance()
        var y = FragmentListPlayer.newInstance()
        y.arguments = bundle
        x.arguments = bundle
        return when (p0) {
            0 -> x
            1 -> y
            else -> x
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Overview"
        1 -> "Players"
//        2 -> "FavoriteMatch"
        else -> "Overview"
    }
}