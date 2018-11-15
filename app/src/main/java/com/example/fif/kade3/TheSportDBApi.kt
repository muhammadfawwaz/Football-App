package com.example.fif.kade3

object TheSportDBApi {
    fun getLastMatch(): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/eventspastleague.php?id=4328"
    }

    fun getNextMatch(): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/eventsnextleague.php?id=4328"
    }

    fun getTeamBadge(idTeam: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupteam.php?id=" + idTeam
    }

    fun getEventDetail(eventId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupevent.php?id=" + eventId
    }
}