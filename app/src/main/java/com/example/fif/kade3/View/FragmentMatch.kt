package com.example.fif.kade3.View

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.R
import kotlinx.android.synthetic.main.fragment_match.view.*

class FragmentMatch : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_match, container, false)
        view.apply {
            val adapter = PagerAdapter(childFragmentManager)
            viewPagerId.adapter = adapter
            tabLayoutId.setupWithViewPager(viewPagerId)
        }
        return view
    }

    companion object {
        fun newInstance(): FragmentMatch = FragmentMatch()
    }
}