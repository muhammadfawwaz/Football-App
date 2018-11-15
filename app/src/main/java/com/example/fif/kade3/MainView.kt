package com.example.fif.kade3

import com.example.fif.kade3.Model.Badge
import com.example.fif.kade3.Model.Event
import com.example.fif.kade3.Model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>)
    fun showEventDetail(data: List<Event>)
}