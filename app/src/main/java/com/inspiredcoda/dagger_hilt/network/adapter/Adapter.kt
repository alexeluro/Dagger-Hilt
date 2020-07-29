package com.inspiredcoda.dagger_hilt.network.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.inspiredcoda.dagger_hilt.R
import com.inspiredcoda.dagger_hilt.network.data.User
import kotlinx.android.synthetic.main.user_layout.view.*

class Adapter(
    val users: List<User>
): RecyclerView.Adapter<Adapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.user_layout,
                    parent,
                    false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.fullName.text = users[position].name
        holder.username.text = users[position].username
        holder.email.text = users[position].email
    }

    override fun getItemCount(): Int {
        Log.d("MainRecyclerviewAdapter", "Numbe of Users: ${users.size}")
        return users.size
    }



    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fullName = itemView.findViewById<TextView>(R.id.name)
        val username = itemView.findViewById<TextView>(R.id.username)
        val email = itemView.findViewById<TextView>(R.id.email)

    }


}