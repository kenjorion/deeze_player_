package com.example.deeze_project.data.dto


import com.google.gson.annotations.SerializedName

data class TracksDTO(
    @SerializedName("artist")
    val artistDTO: ArtistDTO,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("song")
    val song: String
)

data class TrackResponseDTO(@SerializedName("data") val trackList: List<TracksDTO>)
