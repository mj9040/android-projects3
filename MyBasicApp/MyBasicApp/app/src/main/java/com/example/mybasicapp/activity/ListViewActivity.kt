package com.example.mybasicapp.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.graphics.blue
import com.example.mybasicapp.R

class ListViewActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        supportActionBar!!.hide()
        val data = arrayOf("Click for Custom Activity","Java","Kotlin","Android","Laravel","JavaScript","Html","React","MySql","Flutter","Python","Spring","C Language","C++ Language","Php programming","Dart")

         listView  =  findViewById(R.id.listView)

        val myAdapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,data)
        listView.adapter=myAdapter

        listView.onItemClickListener = this

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when (listView.getItemAtPosition(p2).toString()) {
            "Click for Custom Activity" -> startActivity(Intent(this,CustomListViewActivity::class.java))
            "Java" -> show("Java is a object oriented programming")
            "Kotlin" -> show("Kotlin is a JVM based programming")
        }

    }

    private fun show(msg:String)
    {
        Toast.makeText(this, " $msg", Toast.LENGTH_SHORT).show()
    }
}