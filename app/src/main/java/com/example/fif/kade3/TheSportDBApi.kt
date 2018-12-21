package com.example.fif.kade3

import android.os.Build

object TheSportDBApi {
    fun getLastMatch(league: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/eventspastleague.php?id=" + findLeagueCode(league)
    }

    fun getSearchMatch(text: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/searchevents.php?e=" + text
    }

    fun getSearchTeam(text: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/searchteams.php?t=" + text
    }

    fun getPlayerList(teamId: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookup_all_players.php?id=" + teamId
    }

    fun getPlayerDetail(playerId: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupplayer.php?id=" + playerId
    }

    fun getTeamDetail(teamId: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupteam.php?id=" + teamId
    }

    fun getTeamList(league: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/search_all_teams.php?l=" + league
    }

    fun getNextMatch(league: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/eventsnextleague.php?id=" + findLeagueCode(league)
    }

    fun getTeamBadge(idTeam: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupteam.php?id=" + idTeam
    }

    fun getEventDetail(eventId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupevent.php?id=" + eventId
    }

    fun findLeagueCode(league: String):String {
        var leagueCode = ""
        if(league == "English Premier League") {
            leagueCode = "4328"
        }
        else if(league == "English League Championship") {
            leagueCode = "4329"
        }
        else if(league == "German Bundesliga") {
            leagueCode = "4331"
        }
        else if(league == "Italian Serie A") {
            leagueCode = "4332"
        }
        else if(league == "French Ligue 1") {
            leagueCode = "4334"
        }
        else if(league == "Spanish La Liga") {
            leagueCode = "4335"
        }
        return leagueCode
    }
}