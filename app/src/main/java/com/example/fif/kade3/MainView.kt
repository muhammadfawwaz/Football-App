package com.example.fif.kade3

import com.example.fif.kade3.Model.*

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>)
    fun showEventDetail(data: List<Event>)
    fun showTeamList(data: List<Team>)
    fun showPlayerList(data: List<Player>)
}