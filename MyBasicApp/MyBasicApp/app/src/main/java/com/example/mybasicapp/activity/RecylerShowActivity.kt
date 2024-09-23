package com.example.mybasicapp.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mybasicapp.R

class RecylerShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_show)

        supportActionBar!!.title = "Details Screen"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A0F6E2")))

        val profile:ImageView      = findViewById(R.id.showImg)
        val fullName:TextView      = findViewById(R.id.showName)
        val age:TextView           = findViewById(R.id.showAge)
        val batStyle:TextView      = findViewById(R.id.showBat)
        val bollingStyle:TextView  = findViewById(R.id.showBoll)
        val nickname:TextView      = findViewById(R.id.showNickname)
        val teams:TextView         = findViewById(R.id.teams)

        val profilePic   = intent.getIntExtra("profile",R.drawable.virat)

        val name         = intent.getStringExtra("name")
        val forTeam      = intent.getStringExtra("country")
        val batting         = intent.getStringExtra("batting")
        val bolling      = intent.getStringExtra("bolling")
        val shortName         = intent.getStringExtra("nick")
        val playerAge      = intent.getStringExtra("age")

        profile.setImageResource(profilePic)
        fullName.text     = name
        age.text          = playerAge
        batStyle.text     = batting
        bollingStyle.text = bolling
        nickname.text     = shortName
        teams.text        = forTeam


    }
}