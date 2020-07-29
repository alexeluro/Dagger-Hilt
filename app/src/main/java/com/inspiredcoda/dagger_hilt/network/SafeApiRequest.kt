package com.inspiredcoda.dagger_hilt.network

import com.inspiredcoda.dagger_hilt.utils.ApiException
import com.inspiredcoda.dagger_hilt.utils.NoInternetException
import retrofit2.Response
import kotlin.text.StringBuilder

abstract class SafeApiRequest {

    suspend fun <T: Any?>apiRequest(work: suspend()->Response<T>): T{
        val response = work.invoke()
        val message = StringBuilder()

        if (response.isSuccessful){
            return response.body()!!
        }else{

            when(response.code()) {
                500 -> throw ApiException("Service Temporarily Unavailable")

                400 -> throw ApiException("Invalid Data")

                404 -> throw ApiException("Not Found!!!")

                else ->  {
                    message.append("Error Code: ${response.code()}")
                    throw ApiException(message.toString())
                }
            }

        }

    }



}