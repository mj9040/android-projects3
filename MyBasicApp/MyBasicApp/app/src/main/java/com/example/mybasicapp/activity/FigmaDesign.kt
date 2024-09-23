package com.example.mybasicapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybasicapp.R

class FigmaDesign : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figma_design)

        supportActionBar!!.hide()


    }
}