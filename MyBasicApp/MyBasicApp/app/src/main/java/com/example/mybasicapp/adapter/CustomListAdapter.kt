package com.example.mybasicapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mybasicapp.R
import com.example.mybasicapp.activity.Model


class CustomListAdapter(private val context:Activity,var arrayList: ArrayList<Model>):
    ArrayAdapter<Model>(context, R.layout.custom_list_view,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val view:View = convertView?:LayoutInflater.from(context).inflate(R.layout.custom_list_view,parent,false)

        val image:ImageView   = view.findViewById(R.id.image)
        val name  = view.findViewById<TextView>(R.id.name)
        val brand = view.findViewById<TextView>(R.id.brand)

        image.setImageResource(arrayList[position].img)
        name.text  =  arrayList[position].bikeName
        brand.text =  arrayList[position].brand

        return view
    }

}