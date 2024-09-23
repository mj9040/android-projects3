package com.example.mybasicapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybasicapp.R
import com.example.mybasicapp.databinding.ActivityRegisterSuccessBinding

class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name      = intent.getStringExtra("name")
        val email     = intent.getStringExtra("email")
        val mobile    = intent.getStringExtra("mobile")
        val country   = intent.getStringExtra("country")
        val city      = intent.getStringExtra("city")
        val pin       = intent.getStringExtra("pin")
        val pass      = intent.getStringExtra("pass")
        val language  = intent.getStringExtra("language")
        val dob       = intent.getStringExtra("dob")
        val gender    = intent.getStringExtra("gender")
        val hobby     = intent.getStringExtra("hobby")

        binding.showName.text       = name
        binding.showEmail.text      = email
        binding.showMobile.text     = mobile
        binding.showCountry.text    = country
        binding.showCity.text       = city
        binding.showPin.text        = pin
        binding.showPass.text       = pass
        binding.showLanguage.text   = language
        binding.showDob.text        = dob
        binding.showGender.text     = gender
        binding.showHobby.text      = hobby

    }
}