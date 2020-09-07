package com.example.deeze_project.data.api

import com.example.deeze_project.data.dto.TrackResponseDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackAPI {
    @GET("{album_id}/tracks")
    fun getTracks(@Path("album_id") albumId: Int): Response<TrackResponseDTO>

    companion object {
        operator fun invoke(): TrackAPI {
            return Retrofit.Builder()
                .baseUrl("https://api.deezer.com/album")
                //.client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrackAPI::class.java)
        }
    }
}