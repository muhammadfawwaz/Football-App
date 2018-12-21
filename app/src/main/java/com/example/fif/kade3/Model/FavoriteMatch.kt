package com.example.fif.kade3.Model

data class FavoriteMatch(
    val id: Long?,
    val eventId: String?,
    val homeName: String?,
    val awayName: String?,
    val datee: String?,
    val score: String?,
    val homeId: String?,
    val awayId: String?,
    val time: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val SCORE: String = "SCORE"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val TIME: String = "TIME"
    }
}