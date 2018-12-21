package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.activity_fragment_list_team.view.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.support.v4.ctx

class FragmentListTeam : Fragment(), MainView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterRv: TeamAdapter
    private lateinit var pb: ProgressBar
    private lateinit var leagueName: String
    private lateinit var bundle: Bundle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_fragment_list_team, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        view.apply {
            llListTeam.setPadding(0,0,0,150)
            if(arguments != null) {
                bundle = arguments!!
            }
            adapterRv = TeamAdapter(teams, ctx.applicationContext)
            rvListTeam.layoutManager = LinearLayoutManager(ctx.applicationContext)
            rvListTeam.adapter = adapterRv
            pb = pb_list_team_id

            val spinnerItems = resources.getStringArray(R.array.league)
            val spinnerAdapter = ArrayAdapter(ctx,R.layout.support_simple_spinner_dropdown_item,spinnerItems)
            if(arguments == null) {
                spinnerTeamList.adapter = spinnerAdapter
                spinnerTeamList.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                    AdapterView.OnItemSelectedListener {
                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        leagueName = spinnerTeamList.selectedItem.toString()
                        presenter.getTeamList(leagueName)
                    }

                }
            }
            else {
//                llListTeam.setPadding(0,0,0,140)
                spinnerTeamList.invisible()
                presenter.getSearchTeam(bundle.getString("newText"))
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

    override fun showMatchList(data: List<Match>) {

    }

    override fun showTeamBadge(idHomeTeam: List<Badge>, idAwayTeam: List<Badge>) {

    }

    override fun showEventDetail(data: List<Event>) {

    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapterRv.notifyDataSetChanged()
    }

    override fun showPlayerList(data: List<Player>) {

    }

    companion object {
        fun newInstance(): FragmentListTeam = FragmentListTeam()
    }
}