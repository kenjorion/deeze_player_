package com.example.deeze_project.data.repo

import com.example.deeze_project.data.api.TrackAPI
import com.example.deeze_project.data.dto.mapper.TrackResponseMapper
import com.example.deeze_project.data.model.Track

class TrackRepo (
    private val api: TrackAPI
) : ListenerAPIRequest() {

    suspend fun getTracks(albumID: Int): List<Track> {
        val tracksResponseDTO = apiRequest { api.getTracks(albumID) }
        return TrackResponseMapper().map(tracksResponseDTO)
    }

}