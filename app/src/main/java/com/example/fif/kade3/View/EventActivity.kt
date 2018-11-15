package com.example.fif.kade3.View

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.fif.kade3.MainView
import com.example.fif.kade3.Model.*
import com.example.fif.kade3.Presenter.MainPresenter
import com.example.fif.kade3.R
import com.example.fif.kade3.R.id.add_to_favorite
import com.example.fif.kade3.invisible
import com.example.fif.kade3.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event.*
import org.jetbrains.anko.db.*

class EventActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var event: Event? = null
    private var idEvent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        idEvent = intent.getStringExtra("eventId")
        val homeId = intent.getStringExtra("homeId")
        val awayId = intent.getStringExtra("awayId")
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        Log.i("cekidevent", idEvent)
        presenter = MainPresenter(this, request, gson)
        presenter.getTeamBadge(homeId, awayId)
        presenter.getEventDetail(idEvent)
    }

    override fun showLoading() {
        pb_event_id.visible()
    }

    override fun hideLoading() {
        pb_event_id.invisible()
    }

    override fun showMatchList(data: List<Match>) {

    }

    override fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>) {
        Log.i("gambarrumah", idHomeTeam[0].teamBadge)
        Picasso.get().load(idHomeTeam[0].teamBadge).into(homeTeamBadgeId)
        Picasso.get().load(idAwayTeam[0].teamBadge).into(awayTeamBadgeId)
    }

    override fun showEventDetail(data: List<Event>) {
        event = data[0]
        Log.i("isieventnih", event.toString())
        dateEventId.text = data[0].dateEvent
        if (data[0].homeScore != null) {
            scoreEventId.text = data[0].homeScore + getString(R.string.versus) + data[0].awayScore
        } else {
            scoreEventId.text = getString(R.string.versus)
        }
        team1EventId.text = data[0].homeTeam
        team2EventId.text = data[0].awayTeam
        homeGoalDetailsId.text = data[0].homeGoalDetails
        awayGoalDetailsId.text = data[0].awayGoalDetails
        homeShotsId.text = data[0].homeShots
        awayShotsId.text = data[0].awayShots
        if (data[0].homeGoalKeeper != null) {
            homeLineUps.text = data[0].homeGoalKeeper + data[0].homeDefense + data[0].homeMidfield +
                    data[0].homeForward
            awayLineUps.text = data[0].awayGoalKeeper + data[0].awayDefense + data[0].awayMidfield +
                    data[0].awayForward
        }
        homeSub.text = data[0].homeSubtitutes
        awaySub.text = data[0].awaySubtitutes
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        if (event?.homeScore == null) {
            event?.homeScore = ""
            event?.awayScore = ""
        }
        Log.i("iniisimatch", event.toString())
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to event?.idEvent,
                    Favorite.HOME_TEAM_NAME to event?.homeTeam,
                    Favorite.AWAY_TEAM_NAME to event?.awayTeam,
                    Favorite.EVENT_DATE to event?.dateEvent,
                    Favorite.SCORE to event?.homeScore + getString(R.string.versus) + event?.awayScore,
                    Favorite.HOME_ID to event?.idHomeTeam,
                    Favorite.AWAY_ID to event?.idAwayTeam
                )
            }
//            database.use {
//                dropTable(Favorite.TABLE_FAVORITE)
//            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to idEvent)
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_added_to_favorites
            )
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_add_to_favorites
            )
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})", "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
