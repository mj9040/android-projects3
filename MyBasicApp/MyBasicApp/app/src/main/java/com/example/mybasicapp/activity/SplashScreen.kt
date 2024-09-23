package com.example.mybasicapp.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.mybasicapp.R
import com.example.mybasicapp.model.RecycleModel

class SplashScreen : AppCompatActivity() {

      private var progressInt = 0
      private val handler     = Handler()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar!!.title="Kotlin App"

        val progressBar  = findViewById<ProgressBar>(R.id.progressBar)
        val progressText = findViewById<TextView>(R.id.progressText)

        progressBar.visibility = View.VISIBLE
        progressInt =progressBar.progress

        Thread(Runnable {
            while (progressInt<100){
                   progressInt+=5

                handler.post(Runnable {
                    progressBar.progress=progressInt
                    progressText!!.text = progressInt.toString() + " / "+ progressBar.max

                    if(progressInt>=100)
                    {
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }

                })

                try {
                    Thread.sleep(100)
                }
                catch (e:Exception){ e.printStackTrace()}

            }

            progressBar.visibility = View.INVISIBLE
        }).start()

    }
}