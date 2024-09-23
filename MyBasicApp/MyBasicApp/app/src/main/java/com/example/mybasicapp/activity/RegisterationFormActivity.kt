package com.example.mybasicapp.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.example.mybasicapp.R
import com.example.mybasicapp.databinding.ActivityRegisterationFormBinding
import java.text.SimpleDateFormat
import java.util.*

class RegisterationFormActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterationFormBinding
    private lateinit var radioBtn: RadioButton
    private lateinit var gender:String
    private lateinit var languages:Array<String>
    private lateinit var hobby:String
    private lateinit var name:String
    private lateinit var email:String
    private lateinit var mobile:String
    private lateinit var country:String
    private lateinit var city:String
    private lateinit var pin:String
    private lateinit var pass:String
    private lateinit var cnfPass:String
    private lateinit var dob:String
    private lateinit var selectedLanguage:String

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get Dropdown value
        languages = resources.getStringArray(R.array.programming)
       val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_menu,languages)
        binding.edLanguage.setAdapter(arrayAdapter)


       //Get Date Picker value
        binding.edDate.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        val cal   = Calendar.getInstance()
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.edDate.text = sdf.format(cal.time) }

        binding.edDate.setOnClickListener{
            DatePickerDialog(this,dateListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show() }

        //Get Radio Button Value
        binding.radioGroupBtn.setOnCheckedChangeListener{ _, id ->
             radioBtn= findViewById(id)
            gender=radioBtn.text.toString()
            }

        binding.createBioData.setOnClickListener {
            getEditTextValue()
            getCheckBoxValue()
            selectedLanguage = binding.edLanguage.text.toString()

            val intent  =  Intent(this,RegisterSuccessActivity::class.java)

            intent.putExtra("name",name)
            intent.putExtra("email",email)
            intent.putExtra("mobile",mobile)
            intent.putExtra("country",country)
            intent.putExtra("city",city)
            intent.putExtra("pin",pin)
            intent.putExtra("pass",pass)
            intent.putExtra("language",selectedLanguage)
            intent.putExtra("dob",dob)
            intent.putExtra("gender",gender)
            intent.putExtra("hobby",hobby)

            startActivity(intent)
            }

    }

        //Get Check Box value
        private fun getCheckBoxValue()
        {
            hobby=""
        if(binding.cricketbtn.isChecked)  hobby += binding.cricketbtn.text.toString()+", "

        if(binding.footbtn.isChecked)     hobby += binding.footbtn.text.toString()+", "

        if(binding.hockeyBtn.isChecked)   hobby += binding.hockeyBtn.text.toString()+", "

        if(binding.musicBtn.isChecked)    hobby += binding.musicBtn.text.toString()+", "

        if(binding.codingBtn.isChecked)   hobby += binding.codingBtn.text.toString()+", "

        if(binding.dancingBtn.isChecked)  hobby += binding.dancingBtn.text.toString() }

        //Get Edittext Value
        private fun getEditTextValue()
        {   name    = binding.edName.text.toString()
        email   = binding.edEmail.text.toString()
        mobile  = binding.edMobile.text.toString()
        country = binding.edCountry.text.toString()
        city    = binding.edCity.text.toString()
        pin     = binding.edPin.text.toString()
        pass    = binding.edPass.text.toString()
        cnfPass = binding.edCnfPass.text.toString()
        dob     = binding.edDate.text.toString()

        if(name.isEmpty())
        { binding.edName.error = "Name Field can't be empty!"; binding.edName.requestFocus() }

        else if(email.isEmpty())
        { binding.edEmail.error="Email Field can't be empty!"; binding.edEmail.requestFocus()}

        else if(mobile.isEmpty())
        { binding.edMobile.error="Mobile Field can't be empty!"; binding.edMobile.requestFocus()}

        else if(country.isEmpty())
        { binding.edCountry.error="Country Field can't be empty!"; binding.edCountry.requestFocus()}

        else if(city.isEmpty())
        { binding.edCity.error="City Field can't be empty!"; binding.edCity.requestFocus()}

        else if(pin.isEmpty())
        { binding.edPin.error="Pin Field can't be empty!"; binding.edPin.requestFocus()}

        else if(pass.isEmpty())
        { binding.edPass.error="Password Field can't be empty!"; binding.edPass.requestFocus()}

        else if(cnfPass.isEmpty())
        { binding.edCnfPass.error="Confirm Password Field can't be empty!"; binding.edCnfPass.requestFocus()}

        else if(pass != cnfPass)
        { binding.edPass.error="Password Field didn't Match!"; binding.edPass.requestFocus()}

         }


}