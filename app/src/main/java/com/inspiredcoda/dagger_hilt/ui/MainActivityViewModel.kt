package com.inspiredcoda.dagger_hilt.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspiredcoda.dagger_hilt.network.MainRepository
import com.inspiredcoda.dagger_hilt.network.data.User
import com.inspiredcoda.dagger_hilt.utils.ApiException
import com.inspiredcoda.dagger_hilt.utils.NoInternetException
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    private val repository: MainRepository
): ViewModel() {

    var listener: MainListener? = null


    private var _getUser = MutableLiveData<List<User>>()

    init {
        viewModelScope.launch {
            try {
                Log.d("MainActivityViewModel", "Getting all Users")
                _getUser.value = repository.getUsers()
            }catch (e: ApiException){
                Log.d("MainActivityViewModel", "ApiException occurred!!!!!!!!!!!!!!!!!!!!!!!")
                listener?.onFailure(e.message.toString())
            }catch (e: NoInternetException){
                Log.d("MainActivityViewModel", "NoInternetException occurred!!!!!!!!!!!!!!!!!!")
                listener?.onFailure(e.message.toString())
            }
        }
    }


    fun getUsers(): LiveData<List<User>> {
        return _getUser
    }


}