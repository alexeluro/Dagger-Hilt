package com.inspiredcoda.dagger_hilt.network

import android.content.Context
import android.net.ConnectivityManager
import com.inspiredcoda.dagger_hilt.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) {
            throw NoInternetException("No Internet Connection")
        }
        return chain.proceed(chain.request())

    }


    fun isInternetAvailable(): Boolean {
        val conMan =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        conMan.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }


}