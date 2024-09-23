package com.example.mybasicapp.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.mybasicapp.R
import com.example.mybasicapp.databinding.ActivityCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import javax.microedition.khronos.egl.EGL10
import kotlin.properties.Delegates

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title="My Calculator App"

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A0F6E2")))


        binding.zeroBtn.setOnClickListener  {  binding.input.append("0")  }
        binding.oneBtn.setOnClickListener   {  binding.input.append("1")  }
        binding.twoBtn.setOnClickListener   {  binding.input.append("2")  }
        binding.threeBtn.setOnClickListener {  binding.input.append("3")  }
        binding.fourBtn.setOnClickListener  {  binding.input.append("4")  }
        binding.fiveBtn.setOnClickListener  {  binding.input.append("5")  }
        binding.sixBtn.setOnClickListener   {  binding.input.append("6")  }
        binding.sevenBtn.setOnClickListener {  binding.input.append("7")  }
        binding.eightBtn.setOnClickListener {  binding.input.append("8")  }
        binding.nineBtn.setOnClickListener  {  binding.input.append("9")  }
        binding.dotBtn.setOnClickListener   {  binding.input.append(".")  }


        binding.addBtn.setOnClickListener     {  binding.input.append(" + ")  }
        binding.subBtn.setOnClickListener     {  binding.input.append(" - ")  }
        binding.mulBtn.setOnClickListener     {  binding.input.append(" * ")  }
        binding.divideBtn.setOnClickListener  {  binding.input.append(" / ")  }
        binding.percentBtn.setOnClickListener {  binding.input.append(" % ")  }


        binding.equalBtn.setOnClickListener   {
            val expression  =  ExpressionBuilder(binding.input.text.toString()).build()
            val result      =  expression.evaluate()
            val longResult  =  result.toLong()

            if(result == longResult.toDouble()) {   binding.result.text = longResult.toString() }

            else  {  binding.result.text = result.toString() }
        }



        binding.acBtn.setOnClickListener  {
            binding.input.text   = null
            binding.result.text  = null }

        binding.deleteBtn.setOnClickListener  {
            binding.input.text  =  binding.input.text.toString().dropLast(1)
        }

    }
}


