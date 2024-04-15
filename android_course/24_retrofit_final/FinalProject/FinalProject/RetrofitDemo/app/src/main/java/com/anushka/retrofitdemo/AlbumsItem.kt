package com.anushka.retrofitdemo


import com.google.gson.annotations.SerializedName

// data class created using json to kotlin plugin
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)