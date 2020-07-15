package com.example.deeze_project.data.model

import android.os.Parcelable
import com.example.deeze_project.data.dto.ArtistDTO
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Track(
    val artistDTO: @RawValue Any ? = null,
    val duration: Int,
    val id: Int,
    val link: String,
    val rank: Int,
    val title: String,
    val type: String,
    val song: String

) : Parcelable