package com.example.fif.kade3.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fif.kade3.Model.FavoriteMatch
import com.example.fif.kade3.R
import kotlinx.android.synthetic.main.favoritelistitem.view.*
import org.jetbrains.anko.startActivity

class FavoriteAdapter(private val favoriteMatch: List<FavoriteMatch>, private val ctx: Context) :
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
        return favoriteMatch.size
    }

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(favoriteMatch[p1], ctx)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateFavorite: TextView = itemView.dateFavoriteId
        private val homeFavorite: TextView = itemView.homeFavoriteId
        private val awayFavorite: TextView = itemView.AwayFavoriteId
        private val scoreFavorite: TextView = itemView.scoreFavoriteId
        private val timeFavorite: TextView = itemView.timeFavoriteId

        fun bindItem(favoriteMatch: FavoriteMatch, ctx: Context) {
//            Log.i("isifavbind", favoriteMatch.toString())
            dateFavorite.text = favoriteMatch.datee
            homeFavorite.text = favoriteMatch.homeName
            awayFavorite.text = favoriteMatch.awayName
            scoreFavorite.text = favoriteMatch.score
            timeFavorite.text = getTime(favoriteMatch.time!!)
            itemView.setOnClickListener {
                Log.i("terklik","benar")
                ctx.startActivity<EventActivity>(
                    "eventId" to favoriteMatch.eventId,
                    "homeId" to favoriteMatch.homeId,
                    "awayId" to favoriteMatch.awayId
                )
            }
        }

        fun getTime(time: String): String {
            if(time.isEmpty()) {
                return ""
            }
            var hour = time.split(":")[0].toInt()
            var minute = time.split(":")[1].toInt()

            if(hour + 7 < 24) {
                hour += 7
            }
            else {
                hour -= 17
            }

            var hourr = hour.toString()
            var minutee = minute.toString()

            if(hourr.length == 1) {
                hourr = "0" + hourr
            }

            if(minutee.length == 1) {
                minutee = "0" + minutee
            }

            return hourr + ":" + minutee
        }
    }

}