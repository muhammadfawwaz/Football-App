package com.example.fif.kade3.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fif.kade3.Model.Player
import com.example.fif.kade3.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.teamlistitem.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val players: List<Player>, private val context: Context) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerAdapter.PlayerViewHolder {
        return PlayerAdapter.PlayerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.teamlistitem,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(p0: PlayerAdapter.PlayerViewHolder, p1: Int) {
        p0.bindItem(players[p1],context)
    }

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val playerImg = itemView.teamListBadge
        private val playerName = itemView.teamListName
        private val playerPos = itemView.playerPos

        fun bindItem(player: Player,context: Context) {
            playerName.text = player.playerName
            playerPos.text = player.playerPosition
            Picasso.get().load(player.playerImg).into(playerImg)

            itemView.setOnClickListener {
                context.startActivity<PlayerDetailActivity>(
                    "playerId" to player.playerId)
            }
        }
    }
}