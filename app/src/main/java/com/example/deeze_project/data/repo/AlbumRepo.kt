package com.example.deeze_project.data.repo

import com.example.deeze_project.data.api.AlbumAPI
import com.example.deeze_project.data.model.Album
import com.example.deeze_project.data.dto.mapper.AlbumsResponseMapper


class AlbumRepo (
    private val api: AlbumAPI
) : ListenerAPIRequest() {

    suspend fun getAlbums(): List<Album> {
        val albumsResponseDTO = apiRequest { api.getAlbums() }
        return AlbumsResponseMapper().map(albumsResponseDTO)
    }
}