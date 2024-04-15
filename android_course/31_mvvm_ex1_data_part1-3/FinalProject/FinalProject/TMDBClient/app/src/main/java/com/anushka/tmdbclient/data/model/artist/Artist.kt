package com.anushka.tmdbclient.data.model.artist


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// used for room as well as retrofit
@Entity(tableName = "popular_artists")// this is for room
// table name used in query writing
data class Artist(
    @PrimaryKey// this  for room
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)