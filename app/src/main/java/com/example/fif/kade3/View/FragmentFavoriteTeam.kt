package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.Model.FavoriteTeam
import com.example.fif.kade3.Model.Team
import com.example.fif.kade3.Model.database
import com.example.fif.kade3.R
import com.example.fif.kade3.invisible
import kotlinx.android.synthetic.main.activity_fragment_list_team.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx

class FragmentFavoriteTeam : Fragment() {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter

    companion object {
        fun newInstance(): FragmentFavoriteTeam = FragmentFavoriteTeam()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_fragment_list_team, container, false)
        view.apply {
            adapter = TeamAdapter(teams,ctx)
            rvListTeam.layoutManager = LinearLayoutManager(ctx.applicationContext)
            rvListTeam.adapter = adapter
            showFavorite()
            pb_list_team_id.invisible()
            spinnerTeamList.invisible()
        }

        return view
    }

    private fun showFavorite() {
        teams.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            for(fav in favorite) {
                teams.add(Team(fav.teamBadge,fav.teamId,fav.teamName))
            }
            Log.i("test123",teams.toString())
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        showFavorite()
        super.onResume()
    }
}