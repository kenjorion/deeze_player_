package com.example.deeze_project.data.dto


import com.google.gson.annotations.SerializedName

data class ArtistDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)

