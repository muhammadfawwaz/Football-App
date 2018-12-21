package com.example.fif.kade3.Presenter

import com.example.fif.kade3.*
import com.example.fif.kade3.Model.ApiRepository
import com.example.fif.kade3.Model.Event
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainView, private val apiRepository: ApiRepository, private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getLastMatchList(league: String) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(league)).await(),
                MatchResponse::class.java
            )

            view.showMatchList(data.events)
            view.hideLoading()
        }
    }

    fun getSearchTeam(teks: String) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchTeam(teks)).await(),
                TeamResponse::class.java
            )

            if(data.teams != null) {
                view.showTeamList(data.teams)
            }
            view.hideLoading()
        }
    }

    fun getSearchMatch(text: String) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchMatch(text)).await(),
                SearchResponse::class.java
            )
            if(data.event != null) {
                view.showMatchList(data.event)
            }
            view.hideLoading()
        }
    }

    fun getPlayerList(teamId: String) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayerList(teamId)).await(),
                PlayerResponse::class.java
            )

            view.showPlayerList(data.player)
            view.hideLoading()
        }
    }

    fun getPlayerDetail(playerId: String) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                PlayersResponse::class.java
            )

            view.showPlayerList(data.players)
            view.hideLoading()
        }
    }

    fun getTeamList(league: String) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamList(league)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

    fun getNextMatchList(league: String) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(league)).await(),
                MatchResponse::class.java
            )

            view.showMatchList(data.events)
            view.hideLoading()
        }
//        view.showLoading()
//        doAsync {
//            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch()), MatchResponse::class.java)
//            uiThread {
//                view.hideLoading()
//                view.showMatchList(data.events)
//            }
//        }

    }

    fun getTeamBadge(idhomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val dataHome = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamBadge(idhomeTeam)).await(),
                BadgeResponse::class.java
            )
            val dataAway = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamBadge(idAwayTeam)).await(),
                BadgeResponse::class.java
            )

            view.showTeamBadge(dataHome.teams,dataAway.teams)
            view.hideLoading()
        }
//        view.showLoading()
//        doAsync {
//            val dataHome = gson.fromJson(
//                apiRepository.doRequest(TheSportDBApi.getTeamBadge(idhomeTeam)),
//                BadgeResponse::class.java
//            )
//            val dataAway = gson.fromJson(
//                apiRepository.doRequest(TheSportDBApi.getTeamBadge(idAwayTeam)),
//                BadgeResponse::class.java
//            )
//            uiThread {
//                view.hideLoading()
//                view.showTeamBadge(dataHome.teams, dataAway.teams)
//            }
//        }
    }

    fun getEventDetail(eventId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventDetail(eventId)).await(),
                EventResponse::class.java
            )

            view.showEventDetail(data.events)
            view.hideLoading()
        }
//        view.showLoading()
//        Log.i("eventdetailtes", eventId + "sedwefwefergergerhethrthtyj")
//        doAsync {
//            val data =
//                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)), EventResponse::class.java)
//            Log.i("presenterrkowe", data.toString())
//            uiThread {
//                view.hideLoading()
//                view.showEventDetail(data.events)
//            }
//        }
    }
}
