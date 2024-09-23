package com.example.mybasicapp.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts

import com.example.mybasicapp.databinding.ActivityTakePictureBinding


class TakePictureActivity : AppCompatActivity() {

     private lateinit var binding:ActivityTakePictureBinding
     private val cameraCode:Int = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTakePictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Capture Image from camera
        binding.cameraBtn.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             startActivityIfNeeded(intent,cameraCode) }

        //Capture Image from Gallery
         binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            pickImage.launch(intent) }


    }



       //Capture Image from camera
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == cameraCode) {
            val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
            binding.imageView.setImageBitmap(bitmap) }
         }


    ////Capture Image from Gallery
    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if (it.resultCode == Activity.RESULT_OK){
            val data  = it.data
            val imgUri= data?.data
            binding.imageView.setImageURI(imgUri) }
      }



}