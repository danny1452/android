package com.anushka.tmdbclient.data.model.artist


import com.anushka.tmdbclient.data.model.artist.Artist
import com.google.gson.annotations.SerializedName

// generate by json to kotlin plugin
data class ArtistList(
    @SerializedName("results")
    val artists: List<Artist>
)