package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.fif.kade3.*
import com.example.fif.kade3.Model.ApiRepository
import com.example.fif.kade3.Model.Badge
import com.example.fif.kade3.Model.Event
import com.example.fif.kade3.Model.Match
import com.example.fif.kade3.Presenter.MainPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_fragmen_next_match.view.*
import org.jetbrains.anko.support.v4.ctx

class FragmentNextMatch : Fragment(), MainView {
    private var matchs: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterRv: MainAdapter
    private lateinit var pb: ProgressBar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_fragmen_next_match, container, false)
        adapterRv = MainAdapter(matchs, ctx.applicationContext)
        Log.i("oncreate", matchs.toString())
        view.rvNextMatchId.layoutManager = LinearLayoutManager(ctx.applicationContext)
        view.rvNextMatchId.adapter = adapterRv
        pb = view.pb_next_match_id
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getNextMatchList()

        return view
    }

    override fun showLoading() {
        pb.visible()
    }

    override fun hideLoading() {
        pb.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        matchs.clear()
        matchs.addAll(data)
        Log.i("showmatchlist", matchs.toString())
        adapterRv.notifyDataSetChanged()
    }

    override fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>) {

    }

    override fun showEventDetail(data: List<Event>) {

    }

    companion object {
        fun newInstance(): FragmentNextMatch = FragmentNextMatch()
    }
}