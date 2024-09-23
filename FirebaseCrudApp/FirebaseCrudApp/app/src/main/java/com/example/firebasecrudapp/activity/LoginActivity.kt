package com.example.firebasecrudapp.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.firebasecrudapp.MainActivity
import com.example.firebasecrudapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var email:String
    private lateinit var pass:String
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#DD2C00")))

        firebaseAuth = FirebaseAuth.getInstance()
        binding.regText.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

        binding.loginBtn.setOnClickListener {  validateUser() }

    }

    private fun validateUser() {
        email = binding.email.text.toString().trim()
        pass  = binding.pass.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty() )
        {   Toast.makeText(this, "Please provide the credentials", Toast.LENGTH_SHORT).show()  }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {   binding.email.error ="Please provide valid email";  binding.email.requestFocus() }

        else {
             binding.linearProgressIndicator.visibility = View.VISIBLE
            loginProcess()  }
    }

    private fun loginProcess() {
           firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{ task->

               if (task.isSuccessful){
                   binding.email.text.clear()
                   binding.pass.text.clear()
                   binding.linearProgressIndicator.visibility = View.INVISIBLE
                   Toast.makeText(this, "Sign-In Successful", Toast.LENGTH_SHORT).show()
                   startActivity(Intent(this,MainActivity::class.java))
                   finish()
               }
               else {
                    binding.linearProgressIndicator.visibility = View.INVISIBLE
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show() }
           }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!=null)
        {
           startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}