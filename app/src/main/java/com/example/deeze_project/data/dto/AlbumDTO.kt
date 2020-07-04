package com.example.deeze_project.data.dto


import com.google.gson.annotations.SerializedName

data class AlbumDTO(
    @SerializedName("cover")
    val cover: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("nb_tracks")
    val nbTracks: Int,
    @SerializedName("share")
    val share: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("artist")
    val artistDTO: ArtistDTO
)

data class AlbumResponseDTO(@SerializedName("data") val albumList: List<AlbumDTO>)
