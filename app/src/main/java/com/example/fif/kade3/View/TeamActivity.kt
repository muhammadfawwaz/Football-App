package com.example.fif.kade3.View

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.fif.kade3.MainView
import com.example.fif.kade3.Model.*
import com.example.fif.kade3.Presenter.MainPresenter
import com.example.fif.kade3.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.activity_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.image

class TeamActivity : AppCompatActivity(), MainView {
    private var isFavorite: Boolean = false
    private lateinit var team: Team
    private lateinit var presenter: MainPresenter
    private lateinit var teamId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        teamId = intent.getStringExtra("teamId")
        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getTeamDetail(teamId)

        var bundle = Bundle()
        bundle.putString("teamId",teamId)

        val adapter = TeamPager(supportFragmentManager,bundle)
        teamViewPagerId.adapter = adapter
        teamTabLayoutId.setupWithViewPager(teamViewPagerId)
        teamTabLayoutId.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

        })

        favorites_team.setOnClickListener {
            if(isFavorite) {
                removeFromFavorite()
            }
            else {
                addToFavorite()
            }
            isFavorite = !isFavorite
            favoriteState()
        }

        backImg.setOnClickListener {
            finish()
        }
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
        team = data[0]
        Picasso.get().load(data[0].teamBadge).into(teamDetailBadge)
        teamDetailName.text = data[0].teamName
        teamDetailFormed.text = data[0].teamFormed
        teamDetailStadium.text = data[0].teamStadium
    }

    override fun showPlayerList(data: List<Player>) {

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE).whereArgs("(TEAM_ID = {id})", "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
            setFavorite()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            favorites_team.setImageResource(R.drawable.ic_added_to_favorites)
        } else {
            favorites_team.setImageResource(R.drawable.ic_add_to_favorites)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge
                )
            }
            Snackbar.make(activity_team_view,"Tim telah ditambahkan", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE, "(TEAM_ID = {id})", "id" to teamId)
            }
            Snackbar.make(activity_team_view,"Tim telah dihapuskan",Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {

        }
    }
}