package com.example.fif.kade3.View

import android.content.Context
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.Model.Match
import com.example.fif.kade3.Model.Team
import com.example.fif.kade3.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.teamlistitem.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(private val teams: List<Team>, private val context: Context) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamAdapter.TeamViewHolder {
        return TeamAdapter.TeamViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.teamlistitem,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(p0: TeamAdapter.TeamViewHolder, p1: Int) {
        p0.bindItem(teams[p1],context)
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge = itemView.teamListBadge
        private val teamName = itemView.teamListName

        fun bindItem(team: Team,context: Context) {
            teamName.text = team.teamName
            Picasso.get().load(team.teamBadge).into(teamBadge)
            itemView.setOnClickListener {
                Log.i("teamIdbruh",team.teamId)
                context.startActivity<TeamActivity>(
                    "teamId" to team.teamId)
            }
        }
    }

}