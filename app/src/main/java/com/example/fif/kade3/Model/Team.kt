package com.example.fif.kade3.Model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("intFormedYear")
    var teamFormed: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDesc: String? = null
)