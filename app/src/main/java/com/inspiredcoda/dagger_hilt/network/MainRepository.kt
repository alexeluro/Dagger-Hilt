package com.inspiredcoda.dagger_hilt.network

import com.inspiredcoda.dagger_hilt.network.data.User
import com.inspiredcoda.dagger_hilt.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: Api
): SafeApiRequest() {


    suspend fun getUsers(): List<User>{
        return apiRequest { api.getAllUsers() }
    }

}