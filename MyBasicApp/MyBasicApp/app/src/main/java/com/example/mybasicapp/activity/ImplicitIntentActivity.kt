package com.example.mybasicapp.activity

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.mybasicapp.databinding.ActivityImplicitIntentBinding

class ImplicitIntentActivity : AppCompatActivity() {

     private lateinit var binding:ActivityImplicitIntentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImplicitIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A0F6E2")))

        binding.callBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_CALL);
                intent.data= Uri.parse("tel:"+binding.edPhone.text.toString())
                startActivity(intent); }

        binding.dailpadBtn.setOnClickListener {
              val intent  =  Intent(Intent.ACTION_DIAL)
              intent.data =  Uri.parse("tel:8340192015")
              startActivity(intent) }

        binding.contactBtn.setOnClickListener {
            val intent  =  Intent(Intent.ACTION_VIEW)
            intent.data =  Uri.parse("content://contact/people/")
            startActivity(intent) }

        binding.callLogBtn.setOnClickListener {
            val intent  =  Intent(Intent.ACTION_VIEW)
            intent.data =  Uri.parse("content://call_log/calls/")
            startActivity(intent) }

        binding.browserBtn.setOnClickListener {
            val intent  =  Intent(Intent.ACTION_VIEW)
            intent.data =  Uri.parse("https://google.com/")
            startActivity(intent) }

        binding.facebookBtn.setOnClickListener {
            val intent   =  Intent(Intent.ACTION_VIEW)
            intent.data  =  Uri.parse("https://facebook.com/")
            startActivity(intent)  }

        binding.gmailBtn.setOnClickListener {
            val email   =  Intent(Intent.ACTION_SEND)

            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("mdnurnazar786@gmail.com"))
            email.putExtra(Intent.EXTRA_SUBJECT,"Enter Subject")
            email.putExtra(Intent.EXTRA_TEXT,"Enter Message")
            email.setType("message/rfc822")
            startActivity(Intent.createChooser(email,"Choose an Email client :")) }


        binding.sharingBtn.setOnClickListener {
            val appPackageName = this.packageName

            val intent         = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Android")
            intent.putExtra(Intent.EXTRA_TEXT,"Check NNR App at: https://play.google.com/store/apps/details?id=" + appPackageName)
            intent.setType("text/plain")
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)  }

    }
}