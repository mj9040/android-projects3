package com.example.firebasecrudapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.firebasecrudapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var name:String
    private lateinit var email:String
    private lateinit var pass:String
    private lateinit var cnfPass:String


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database:FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database     = FirebaseDatabase.getInstance()

        binding.registerBtn.setOnClickListener {
            validateUserInput() }

        binding.loginText.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()}
    }






    private fun validateUserInput() {
        name   = binding.name.text.toString().trim()
        email  = binding.email.text.toString().trim()
        pass   = binding.pass.text.toString().trim()
        cnfPass= binding.cnfPass.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || cnfPass.isEmpty())
        {   Toast.makeText(this, "Please provide the credentials", Toast.LENGTH_SHORT).show()  }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {   binding.email.error ="Please provide valid email";  binding.email.requestFocus() }

        else if(pass.length<=5)    { binding.pass.error ="Password length should be greater than 5";  binding.pass.requestFocus() }

        else if (pass!=cnfPass)    { binding.cnfPass.error = "Didn't Match Password";  binding.cnfPass.requestFocus(); }

        else  {
            binding.linearProgressIndicator.visibility = View.VISIBLE
            registerUser()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun registerUser()
    {
        val userData = HashMap<String,String>()
        userData["name"] = name
        userData["email"] = email
        userData["pass"] = pass


        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){ task->

              if (task.isSuccessful)
              {
                 val id = task.result.user?.uid!!
                  database.reference.child("User").child(id).setValue(userData).addOnSuccessListener{

                      binding.linearProgressIndicator.visibility = View.INVISIBLE
                      Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                      startActivity(Intent(this,LoginActivity::class.java))
                      finish()

                  }.addOnFailureListener {
                      binding.linearProgressIndicator.visibility = View.INVISIBLE
                      Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
                  }

                  else {
                  binding.linearProgressIndicator.visibility = View.INVISIBLE
                  Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show() } }
         }

}