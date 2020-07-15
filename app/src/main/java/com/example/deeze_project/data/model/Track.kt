package com.example.deeze_project.data.model

import com.example.deeze_project.data.dto.ArtistDTO

data class Track(
    val artistDTO: ArtistDTO,
    val duration: Int,
    val id: Int,
    val link: String,
    val rank: Int,
    val title: String,
    val type: String,
    val song: String

)