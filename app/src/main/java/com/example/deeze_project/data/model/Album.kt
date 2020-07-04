package com.example.deeze_project.data.model

import com.example.deeze_project.data.dto.ArtistDTO

data class Album(
    val cover: String,
    val duration: Int,
    val id: Int,
    val label: String,
    val link: String,
    val nbTracks: Int,
    val share: String,
    val title: String,
    val tracklist: String,
    val artistDTO: ArtistDTO
)