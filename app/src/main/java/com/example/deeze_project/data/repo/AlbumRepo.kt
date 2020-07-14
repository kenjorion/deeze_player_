package com.example.deeze_project.data.repo

import com.example.deeze_project.data.api.AlbumAPI
import com.example.deeze_project.data.model.Album
import com.example.deeze_project.data.dto.mapper.AlbumsResponseMapper


// Le design pattern Repository fait le lien entre le viewModel et les sources de données (model, dto)
// la source de donnée ici est l'API avec Retrofit
class AlbumRepo (
    private val api: AlbumAPI
) : ListenerAPIRequest() {

    suspend fun getAlbums(): List<Album> {
        val albumsResponseDTO = apiRequest { api.getAlbums() } // { ... } func call
        return AlbumsResponseMapper().map(albumsResponseDTO)
    }
}