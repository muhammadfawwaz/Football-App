package com.example.fif.kade3.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fif.kade3.MainView
import com.example.fif.kade3.Model.*
import com.example.fif.kade3.Presenter.MainPresenter
import com.example.fif.kade3.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private lateinit var playerId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        playerId = intent.getStringExtra("playerId")

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getPlayerDetail(playerId)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

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
        Picasso.get().load(data[0].playerHeader).into(playerHeader)
        playerHeight.text = data[0].playerHeight
        playerWeight.text = data[0].playerWeight
        playerDetailPos.text = data[0].playerPosition
        playerDetail.text = data[0].playerDesc
        playerName.text = data[0].playerName
    }
}
