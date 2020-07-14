package com.example.deeze_project.data.api

import com.example.deeze_project.data.dto.AlbumResponseDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AlbumAPI {
    @GET("album")
    suspend fun getAlbums(): Response<AlbumResponseDTO>

    companion object {
        operator fun invoke(): AlbumAPI {
            return Retrofit.Builder()
                .baseUrl("http://api.deezer.com/2.0/user/2529/")
                //.client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AlbumAPI::class.java)
        }
    }

    /*    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url
                val builder = url.newBuilder()
                    .addQueryParameter(PARAM_API_KEY, BuildConfig.FLICKR_API_KEY)
                    .addQueryParameter(PARAM_FORMAT, BuildConfig.FLICKR_API_FORMAT)
                    .addQueryParameter(PARAM_NOJSON_CALLBACK, BuildConfig.FLICKR_API_CALLBACK)

                val newUrl = builder.build()
                val newRequest = request.newBuilder().url(newUrl).build()

                chain.proceed(newRequest)
            }.build()
    }*/
}