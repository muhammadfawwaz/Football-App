package com.example.fif.kade3.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fif.kade3.Model.Favorite
import com.example.fif.kade3.R
import kotlinx.android.synthetic.main.favoritelistitem.view.*
import org.jetbrains.anko.startActivity

class FavoriteAdapter(private val favorite: List<Favorite>, private val ctx: Context) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.favoritelistitem,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(favorite[p1], ctx)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateFavorite: TextView = itemView.dateFavoriteId
        private val homeFavorite: TextView = itemView.homeFavoriteId
        private val awayFavorite: TextView = itemView.AwayFavoriteId
        private val scoreFavorite: TextView = itemView.scoreFavoriteId

        fun bindItem(favorite: Favorite, ctx: Context) {
//            Log.i("isifavbind", favorite.toString())
            dateFavorite.text = favorite.datee
            homeFavorite.text = favorite.homeName
            awayFavorite.text = favorite.awayName
            scoreFavorite.text = favorite.score
            itemView.setOnClickListener {
                Log.i("terklik","benar")
                ctx.startActivity<EventActivity>(
                    "eventId" to favorite.eventId,
                    "homeId" to favorite.homeId,
                    "awayId" to favorite.awayId
                )
            }
        }
    }

}