package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_team_desc.*
import kotlinx.android.synthetic.main.fragment_team_desc.view.*

class FragmentTeamDesc : Fragment(), MainView {
    private lateinit var presenter: MainPresenter
    private lateinit var bundle: Bundle
    private lateinit var pb: ProgressBar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_desc, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        view.apply {
            Log.i("kuwat", arguments.toString())
            bundle = arguments!!
            pb= pb_overview
            presenter.getTeamDetail(bundle.getString("teamId"))
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

    override fun showPlayerList(data: List<Player>) {

    }

    override fun showTeamList(data: List<Team>) {
        teamDesc.text = data[0].teamDesc
    }

    companion object {
        fun newInstance(): FragmentTeamDesc = FragmentTeamDesc()
    }
}