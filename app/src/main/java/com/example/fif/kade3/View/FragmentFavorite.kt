package com.example.fif.kade3.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.Model.Favorite
import com.example.fif.kade3.Model.database
import com.example.fif.kade3.R
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx

class FragmentFavorite : Fragment() {
    private var event: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {
        fun newInstance(): FragmentFavorite = FragmentFavorite()
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        adapter = FavoriteAdapter(event, ctx)
//        layoutManager = LinearLayoutManager(ctx)
//        rvFavorite.layoutManager = layoutManager
//        rvFavorite.adapter = adapter
//        showFavorite()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.activity_favorite, container, false)
        val view = inflater.inflate(R.layout.activity_favorite, container, false)
        adapter = FavoriteAdapter(event, ctx.applicationContext)
//        Log.i("oncreate", matchs.toString())
        view.rvFavorite.layoutManager = LinearLayoutManager(ctx.applicationContext)
        view.rvFavorite.adapter = adapter
        showFavorite()

        return view
    }

    private fun showFavorite() {
        event.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
//            Log.i("isishowfav", favorite.toString())
            event.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        showFavorite()
        super.onResume()
    }
}