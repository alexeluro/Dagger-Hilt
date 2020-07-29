package com.inspiredcoda.dagger_hilt.ui

interface MainListener {

    fun onLoading()

    fun onSuccess()

    fun onSuccess(message: String)

    fun onFailure(message: String)


}