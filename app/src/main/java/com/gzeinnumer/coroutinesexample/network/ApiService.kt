package com.gzeinnumer.coroutinesexample.network

import com.gzeinnumer.coroutinesexample.model.ResponseNews
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //coroutines
    //?q=tesla&apiKey=e5430ac2a413408aaafdf60bfa27a874
    @GET("/everything")
    fun getPostFromUserCoroutines(
            @Query("1") q: String,
            @Query("apiKey") apiKey: String
    ): Deferred<Response<ResponseNews>>
}