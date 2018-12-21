package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.fif.kade3.MainView
import com.example.fif.kade3.Model.*
import com.example.fif.kade3.Presenter.MainPresenter
import com.example.fif.kade3.R
import com.example.fif.kade3.invisible
import com.example.fif.kade3.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_fragment_list_team.*
import kotlinx.android.synthetic.main.activity_fragment_list_team.view.*
import org.jetbrains.anko.padding
import org.jetbrains.anko.support.v4.ctx

class FragmentListPlayer : Fragment(), MainView {
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterRv: PlayerAdapter
    private lateinit var bundle: Bundle
    private lateinit var pb: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_fragment_list_team, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        view.apply {
            frameListPlayer.setPadding(0,590,0,0)
            llListTeam.setPadding(0,0,0,0)
            adapterRv = PlayerAdapter(players, ctx.applicationContext)
            rvListTeam.layoutManager = LinearLayoutManager(ctx.applicationContext)
            rvListTeam.adapter = adapterRv
            bundle = arguments!!
            pb = pb_list_team_id
            presenter.getPlayerList(bundle.getString("teamId"))
        }

        return view

    }

    override fun showLoading() {
        pb.visible()
    }

    override fun hideLoading() {
        pb.invisible()
    }

    override fun showMatchList(data: List<Match>) {

    }

    override fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>) {

    }

    override fun showEventDetail(data: List<Event>) {

    }

    override fun showTeamList(data: List<Team>) {

    }

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapterRv.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): FragmentListPlayer = FragmentListPlayer()
    }
}