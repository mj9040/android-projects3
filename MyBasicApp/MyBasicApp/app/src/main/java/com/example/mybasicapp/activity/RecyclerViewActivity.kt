package com.example.mybasicapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybasicapp.R
import com.example.mybasicapp.adapter.RecycleAdapter
import com.example.mybasicapp.model.RecycleModel

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var listModel:ArrayList<RecycleModel>
    private lateinit var recycleAdapter: RecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        supportActionBar!!.hide()

        val recycler:RecyclerView  =  findViewById(R.id.recycle)

         listModel          = ArrayList<RecycleModel>()
         recycleAdapter     = RecycleAdapter(listModel,this)
         addItem()

        recycler.layoutManager   =  LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        recycler.adapter         =  recycleAdapter



    }




    private fun addItem() {
        listModel.add(RecycleModel(R.drawable.virat,  "Virat Kohli",      "India",       "Kohli",    "Right Handed Bat", "Right-arm Medium",     "35 Years"))
        listModel.add(RecycleModel(R.drawable.abd,    "AB De Villiers",   "South Africa","Villiers", "Right Handed Bat", "Right-arm Medium",     "38 Years"))
        listModel.add(RecycleModel(R.drawable.surya,  "Surya kumar Yadav","India",       "Surya",    "Right Handed Bat", "Right-arm Offbreak",   "28 Years"))
        listModel.add(RecycleModel(R.drawable.dhoni,  "MS Dhoni",         "India",       "Dhoni",    "Right Handed Bat", "Right-arm Medium",     "41 Years"))
        listModel.add(RecycleModel(R.drawable.rohit,  "Rohit Sharma",     "India",       "Rohit",    "Right Handed Bat", "Right-arm Offbreak",   "35 Years"))
        listModel.add(RecycleModel(R.drawable.hardik, "Hardik Pandya",    "India",       "Pandya",   "Right Handed Bat", "Right-arm fast-medium","29 Years"))
        listModel.add(RecycleModel(R.drawable.baber,  "Babar Azam",       "Pakistan",    "Babar",    "Right Handed Bat", "Right-arm Legbreak",   "24 Years"))
        listModel.add(RecycleModel(R.drawable.pant,   "Rishabh Pant",     "India",       "Pant",     "Right Handed Bat", "Right-arm Medium",     "28 Years"))
        listModel.add(RecycleModel(R.drawable.gayle,  "Chris Gayle",      "West Indies", "Gayle",    "Left Handed Bat",  "Right-arm Offbreak",   "25 Years"))
        listModel.add(RecycleModel(R.drawable.buttler,"Jos Buttler",      "England",     "Buttler",  "Left Handed Bat",  "Right-arm Offbreak",   "32 Years"))
        listModel.add(RecycleModel(R.drawable.sachin, "Sachin Tendulkar", "India",       "Tendulkar","Right Handed Bat", "Right-arm Medium",     "43 Years"))
        listModel.add(RecycleModel(R.drawable.warner, "David Warnar",     "Australia",   "Warnar",   "Right Handed Bat", "Right-arm Legbreak",   "36 Years"))
        listModel.add(RecycleModel(R.drawable.akhtar, "Shoaib Akhtar",    "Pakistan",    "Akhtar",   "Left Handed Bat",  "Right-arm Legbreak",   "47 Years"))
        listModel.add(RecycleModel(R.drawable.maxwell, "Glenn Maxwell",    "Australia",   "Maxwell",  "Right Handed Bat", "Right-arm Fast",       "34 Years"))
        listModel.add(RecycleModel(R.drawable.aiden,  "Aiden Markram",    "South Africa","Markram",  "Right Handed Bat", "Right-arm Offbreak",   "32 Years"))
        listModel.add(RecycleModel(R.drawable.moen,   "Moen Ali",         "England",     "Moen",     "Right Handed Bat", "Right-arm Offbreak",   "32 Years"))
        listModel.add(RecycleModel(R.drawable.rashid, "Rashid khan",      "Afghanistan", "Rashid",   "Left Handed Bat",  "Right-arm Offbreak",   "28 Years"))
        listModel.add(RecycleModel(R.drawable.kane,   "Kane Williamson",  "New Zeland",  "Kane",     "Right Handed Bat", "Right-arm Offbreak",   "37 Years"))
        listModel.add(RecycleModel(R.drawable.shahin, "Shaheen Afridi",   "Pakistan",    "Shaheen",  "Left Handed Bat",  "Left-arm fast-medium", "28 Years"))
        listModel.add(RecycleModel(R.drawable.bumrah, "Jasprit Bumrah",   "India",       "Bumrah",   "Right Handed Bat", "Right-arm Fast",       "30 Years"))

}

}