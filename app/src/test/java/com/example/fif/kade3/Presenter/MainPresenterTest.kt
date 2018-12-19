package com.example.fif.kade3.Presenter

import com.example.fif.kade3.MainView
import com.example.fif.kade3.MatchResponse
import com.example.fif.kade3.Model.ApiRepository
import com.example.fif.kade3.Model.Match
import com.example.fif.kade3.TestContextProvider
import com.example.fif.kade3.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    private lateinit var presenter: MainPresenter

    @Test
    fun getLastMatchList() {
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
        val teams: MutableList<Match> = mutableListOf()
        val response = MatchResponse(teams)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLastMatch()).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLastMatchList()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson)
    }
}