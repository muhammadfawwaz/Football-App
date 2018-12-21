package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.example.fif.kade3.*
import com.example.fif.kade3.Model.*
import com.example.fif.kade3.Presenter.MainPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_fragmen_last_match.*
import kotlinx.android.synthetic.main.activity_fragmen_last_match.view.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.support.v4.ctx

class FragmentLastMatch : Fragment(), MainView {
    private var matchs: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterRv: MainAdapter
    private lateinit var pb: ProgressBar
    private lateinit var leagueName: String
    private lateinit var bundle: Bundle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_fragmen_last_match, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        view.apply {
            adapterRv = MainAdapter(matchs, ctx.applicationContext)
            rvLastMatchId.layoutManager = LinearLayoutManager(ctx.applicationContext)
            rvLastMatchId.adapter = adapterRv
            pb = pb_last_match_id
            Log.i("argumentscheck",arguments.toString())
            if(arguments != null) {
                bundle = arguments!!
            }

            val spinnerItems = resources.getStringArray(R.array.league)
            val spinnerAdapter = ArrayAdapter(ctx,R.layout.support_simple_spinner_dropdown_item,spinnerItems)

            if(arguments == null) {
                spinnerLast.adapter = spinnerAdapter
                spinnerLast.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                    AdapterView.OnItemSelectedListener {
                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        leagueName = spinnerLast.selectedItem.toString()
                        presenter.getLastMatchList(leagueName)
                    }

                }
            }
            else {
                llLastMatch.setPadding(0,0,0,170)
                spinnerLast.invisible()
                presenter.getSearchMatch(bundle.getString("newText"))
            }
        }

        return view
    }

    override fun showLoading() {
        pb.visible()
    }

    override fun hideLoading() {
        pb.invisible()
    }

    override fun showMatchList(datas: List<Match>) {
        matchs.clear()
        for(data in datas) {
            if(data.sport == "Soccer") {
                matchs.add(data)
            }
        }
        adapterRv.notifyDataSetChanged()
    }

    override fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>) {

    }

    override fun showEventDetail(data: List<Event>) {

    }

    override fun showTeamList(data: List<Team>) {

    }

    override fun showPlayerList(data: List<Player>) {

    }

    companion object {
        fun newInstance(): FragmentLastMatch = FragmentLastMatch()
    }
}