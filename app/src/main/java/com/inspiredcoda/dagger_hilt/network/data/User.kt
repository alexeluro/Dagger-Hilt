package com.inspiredcoda.dagger_hilt.network.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("username")
    val username: String,
    @Expose
    @SerializedName("email")
    val email: String
    )