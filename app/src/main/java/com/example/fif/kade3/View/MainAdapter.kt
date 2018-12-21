package com.example.fif.kade3.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.Model.Match
import com.example.fif.kade3.R
import kotlinx.android.synthetic.main.listitem.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class MainAdapter(private val matchs: List<Match>, private val context: Context) :
    RecyclerView.Adapter<MainAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.listitem,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return matchs.size
    }

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.bindItem(matchs[p1], context)
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val team1 = itemView.team1MatchId
        private val team2 = itemView.team2MatchId
        private val dateMatch = itemView.dateMatchId
        private val scoreMatch = itemView.scoreMatchId
        private val timeMatch = itemView.timeMatchId

        fun bindItem(match: Match, context: Context) {
            team1.text = match.homeTeam
            team2.text = match.awayTeam

            dateMatch.text = match.dateEvent
            if(match.time == null) {
                match.time = ""
            }
            timeMatch.text = getTime(match.time!!)
            if (match.homeScore != null) {
                scoreMatch.text = match.homeScore + context.getString(R.string.versus) + match.awayScore
            } else {
                scoreMatch.text = context.getString(R.string.versus)
            }
            itemView.setOnClickListener {
                context.startActivity<EventActivity>(
                    "eventId" to match.idEvent,
                    "homeId" to match.idHomeTeam,
                    "awayId" to match.idAwayTeam
                )
            }
        }

        fun getTime(time: String): String {
            if(time.isEmpty()) {
                return ""
            }
            var hour = time.split(":")[0].toInt()
            var minute = time.split(":")[1].toInt()

            if(hour + 7 < 24) {
                hour += 7
            }
            else {
                hour -= 17
            }

            var hourr = hour.toString()
            var minutee = minute.toString()

            if(hourr.length == 1) {
                hourr = "0" + hourr
            }

            if(minutee.length == 1) {
                minutee = "0" + minutee
            }

            return hourr + ":" + minutee
        }
    }

}