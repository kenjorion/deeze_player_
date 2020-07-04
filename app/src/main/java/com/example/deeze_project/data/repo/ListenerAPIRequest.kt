package com.example.deeze_project.data.repo

import retrofit2.Response
import java.io.IOException

// class abstraite qui va faire req async http et traiter la reponse
abstract class ListenerAPIRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke() // lance req retrofit
        if(response.isSuccessful){
            return response.body()!!
        }else{
            throw ApiException(response.code().toString())
        }
    }
}

class ApiException(message: String): IOException(message)