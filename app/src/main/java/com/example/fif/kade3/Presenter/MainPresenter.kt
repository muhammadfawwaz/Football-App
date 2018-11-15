package com.example.fif.kade3.Presenter

import android.util.Log
import com.example.fif.kade3.*
import com.example.fif.kade3.Model.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView, private val apiRepository: ApiRepository, private val gson: Gson) {
    fun getLastMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLastMatch()), MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                Log.i("presenterr", TheSportDBApi.getLastMatch())
                view.showMatchList(data.events)
            }
        }

    }

    fun getNextMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch()), MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }

    }

    fun getTeamBadge(idhomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()
        doAsync {
            val dataHome = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamBadge(idhomeTeam)),
                BadgeResponse::class.java
            )
            val dataAway = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamBadge(idAwayTeam)),
                BadgeResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeamBadge(dataHome.teams, dataAway.teams)
            }
        }
    }

    fun getEventDetail(eventId: String?) {
        view.showLoading()
        Log.i("eventdetailtes", eventId + "sedwefwefergergerhethrthtyj")
        doAsync {
            val data =
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)), EventResponse::class.java)
            Log.i("presenterrkowe", data.toString())
            uiThread {
                view.hideLoading()
                view.showEventDetail(data.events)
            }
        }
    }
}
