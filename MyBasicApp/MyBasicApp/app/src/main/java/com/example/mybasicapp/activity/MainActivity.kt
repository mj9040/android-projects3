package com.example.mybasicapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.ImageView
import android.widget.ViewFlipper
import com.example.mybasicapp.R


class MainActivity : AppCompatActivity() {

 private lateinit var  viewFlipper:ViewFlipper
 private lateinit var img:Array<Int>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }

         viewFlipper    = findViewById<ViewFlipper>(R.id.viewFlipper)
        val figmaBtn       = findViewById<Button>(R.id.figma)
        val listBtn        = findViewById<Button>(R.id.list)
        val calcy          = findViewById<Button>(R.id.calcy)
        val recyle         = findViewById<Button>(R.id.recycle)
        val intentFunction = findViewById<Button>(R.id.IntentFunction)
        val alertDialog    = findViewById<Button>(R.id.alertDialog)
        val clickPic       = findViewById<Button>(R.id.clickPic)
        val registerBtn    = findViewById<Button>(R.id.registerBtn)

        img = arrayOf(R.drawable.rc,R.drawable.jawa,R.drawable.ninjah2,R.drawable.ns)


         for(item in img)
        {
            setViewFlipper(item)
                 }


        figmaBtn.setOnClickListener { startActivity(Intent(this, FigmaDesign::class.java)) }

        listBtn.setOnClickListener { startActivity(Intent(this,ListViewActivity::class.java))}

        calcy.setOnClickListener { startActivity(Intent(this,CalculatorActivity::class.java)) }

        recyle.setOnClickListener { startActivity(Intent(this,RecyclerViewActivity::class.java)) }

        intentFunction.setOnClickListener { startActivity(Intent(this,ImplicitIntentActivity::class.java)) }

        alertDialog.setOnClickListener { startActivity(Intent(this,AlertDialogActivity::class.java)) }

        clickPic.setOnClickListener { startActivity(Intent(this,TakePictureActivity::class.java)) }

        registerBtn.setOnClickListener { startActivity(Intent(this,RegisterationFormActivity::class.java)) }

    }

    private fun setViewFlipper(img:Int){
        val imageView =  ImageView(this)
        imageView.setImageResource(img)
        viewFlipper.addView(imageView)
        viewFlipper.isAutoStart=true
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left)
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right)
    }


}