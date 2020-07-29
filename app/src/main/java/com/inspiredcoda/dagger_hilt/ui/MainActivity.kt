package com.inspiredcoda.dagger_hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.inspiredcoda.dagger_hilt.R
import com.inspiredcoda.dagger_hilt.network.adapter.Adapter
import com.inspiredcoda.dagger_hilt.utils.hide
import com.inspiredcoda.dagger_hilt.utils.show
import com.inspiredcoda.dagger_hilt.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainListener {

    val mainActivityViewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel.listener = this
//        mainActivityViewModel = ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
        mainActivityViewModel.getUsers().observe(this, Observer {
            progress.show()
            if (it != null){
                Log.d("MainActivity", it.toString())
                initRecyclerView(Adapter(it))
            }
        })


    }


    private fun initRecyclerView(adapter: Adapter){
        recycler_view.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
        progress.hide()
    }

    override fun onLoading() {
        progress.show()
    }

    override fun onSuccess() {
        progress.hide()
    }

    override fun onSuccess(message: String) {
        this.toast(message)
        progress.hide()
    }

    override fun onFailure(message: String) {
        this.toast(message)
        progress.hide()
    }

}
