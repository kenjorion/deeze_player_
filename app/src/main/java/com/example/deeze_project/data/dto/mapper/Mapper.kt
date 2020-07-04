package com.example.deeze_project.data.dto.mapper

import com.example.deeze_project.data.dto.AlbumResponseDTO
import com.example.deeze_project.data.dto.ArtistDTO
import com.example.deeze_project.data.dto.TrackResponseDTO
import com.example.deeze_project.data.model.Album
import com.example.deeze_project.data.model.Track

class AlbumsResponseMapper {

    // map AlbumResponseDTO en List<Album>
    fun map(albumsResponse: AlbumResponseDTO): List<Album> {
        val albumListDTO = albumsResponse.albumList // = "data" du JSON

        return albumListDTO.map { albumDto ->
            Album(
                albumDto.cover,
                albumDto.duration,
                albumDto.id,
                albumDto.label,
                albumDto.link,
                albumDto.nbTracks,
                albumDto.share,
                albumDto.title,
                albumDto.trackList,
                ArtistDTO(
                    albumDto.artistDTO.id,
                    albumDto.artistDTO.name,
                    albumDto.artistDTO.type
                )
            )
        }
    }
}

class TrackResponseMapper {

    fun map(tracksResponse: TrackResponseDTO): List<Track> {
        val trackListDTO = tracksResponse.trackList // = "data" du JSON

        return trackListDTO.map { trackDto ->
            Track(
                ArtistDTO(
                    trackDto.artistDTO.id,
                    trackDto.artistDTO.name,
                    trackDto.artistDTO.type
                ),
                trackDto.duration,
                trackDto.id,
                trackDto.link,
                trackDto.rank,
                trackDto.title,
                trackDto.type
            )
        }
    }
}