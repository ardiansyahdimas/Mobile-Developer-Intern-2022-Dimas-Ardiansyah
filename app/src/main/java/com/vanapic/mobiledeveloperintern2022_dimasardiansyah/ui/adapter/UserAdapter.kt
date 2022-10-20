package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.R
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.response.UserResponse
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.databinding.ListItemBinding
import java.util.ArrayList

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var listData = ArrayList<UserResponse>()
    var onItemClick: ((UserResponse) -> Unit)? = null

    fun setData(newListData: List<UserResponse>?) {
        if (newListData == null) return
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bind(data: UserResponse) {
            with(binding) {
                Glide.with(itemView.context.applicationContext)
                    .load(data.avatar)
                    .into(userImage)
                tvFirstnameLastname.text = data.firstName + " " + data.lastName
                tvUserEmail.text = data.email
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}