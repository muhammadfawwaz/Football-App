package com.example.fif.kade3.Model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null,

    @SerializedName("strThumb")
    var playerImg: String? = null,

    @SerializedName("strFanart1")
    var playerHeader: String? = null,

    @SerializedName("strDescriptionEN")
    var playerDesc: String? = null,

    @SerializedName("strWeight")
    var playerWeight: String? = null,

    @SerializedName("strHeight")
    var playerHeight: String? = null
    )