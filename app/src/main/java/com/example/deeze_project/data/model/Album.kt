package com.example.deeze_project.data.model

import android.os.Parcelable
import com.example.deeze_project.data.dto.ArtistDTO
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
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
    val artistDTO: @RawValue ArtistDTO
) : Parcelable