package com.example.mybasicapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybasicapp.R
import com.example.mybasicapp.activity.RecylerShowActivity
import com.example.mybasicapp.model.RecycleModel

class RecycleAdapter(private val dataList:List<RecycleModel>,val context:Activity): RecyclerView.Adapter<RecycleAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_recycle_view,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val model = dataList[position]

        holder.image.setImageResource(model.image)
        holder.batterName.text = model.name
        holder.country.text    = model.country

        holder.layout.setOnClickListener(){

            val intent = Intent(context,RecylerShowActivity::class.java)

            intent.putExtra("profile",dataList[position].image)
            intent.putExtra("name",dataList[position].name)
            intent.putExtra("country",dataList[position].country)
            intent.putExtra("batting",dataList[position].batting)
            intent.putExtra("bolling",dataList[position].bolling)
            intent.putExtra("nick",dataList[position].nickName)
            intent.putExtra("age",dataList[position].age)

            context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class MyViewHolder(ItemView:View): RecyclerView.ViewHolder(ItemView) {

        val image:ImageView      =  ItemView.findViewById(R.id.batterImage)
        val batterName:TextView  =  ItemView.findViewById(R.id.batterName)
        val country:TextView     =  ItemView.findViewById(R.id.country)

        val layout:LinearLayout  =  ItemView.findViewById(R.id.layout)
    }
}