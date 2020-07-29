package com.inspiredcoda.dagger_hilt.network

import com.inspiredcoda.dagger_hilt.network.data.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

interface Api {

    @GET("users")
    suspend fun getAllUsers(): Response<List<User>>


    companion object{
        fun invoke(retrofit: Retrofit): Api{
            return retrofit.create(Api::class.java)
        }

    }

}