package com.example.fif.kade3.Model

import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)