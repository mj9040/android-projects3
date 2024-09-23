package com.example.firebasecrudapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasecrudapp.databinding.CustomShowMobileBinding
import com.example.firebasecrudapp.model.Mobiles
import com.squareup.picasso.Picasso

class MobileAdapter(private val deleteUser:(Mobiles)->Unit, private val updateUser:(Mobiles)->Unit) : ListAdapter<Mobiles, MobileAdapter.UserViewHolder>(CompareDiffUtil()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = CustomShowMobileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bindData(it)
        }
    }


    inner class UserViewHolder(private val binding:CustomShowMobileBinding) : RecyclerView.ViewHolder(binding.root)
    {
          @SuppressLint("SetTextI18n")
          fun bindData(mobile: Mobiles)
          {
             Picasso.get().load(mobile.image).into(binding.image)
              binding.name.text =  mobile.name
              binding.color.text = "Color : "+mobile.color
              binding.price.text = "INR "+mobile.price

              binding.delete.setOnClickListener {
                  deleteUser(mobile)
              }

              binding.edit.setOnClickListener {
                  updateUser(mobile)
              }

          }
    }

     class CompareDiffUtil : DiffUtil.ItemCallback<Mobiles>(){
        override fun areItemsTheSame(oldItem: Mobiles, newItem: Mobiles): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Mobiles, newItem: Mobiles): Boolean {
            return oldItem == newItem
        }

    }

}