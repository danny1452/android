package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.artist.Artist

// just interface actual impl in data layer
interface ArtistRepository {
    suspend fun getArtists():List<Artist>?
    suspend fun updateArtists():List<Artist>?
}