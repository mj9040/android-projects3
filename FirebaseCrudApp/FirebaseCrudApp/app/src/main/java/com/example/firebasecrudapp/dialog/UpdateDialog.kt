package com.example.firebasecrudapp.dialog

import android.app.Activity
import android.app.Dialog
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.firebasecrudapp.R
import com.example.firebasecrudapp.model.Mobiles
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class UpdateDialog(context:Activity,mobiles: Mobiles) {
    private var dialog:Dialog
    private var edName: EditText
    private var edColor:EditText
    private var edPrice:EditText
    private var updateBtn: Button
    private var backBtn: Button
    private var image:ImageView

    private lateinit var name:String
    private lateinit var color:String
    private lateinit var price:String
    private lateinit var database: FirebaseDatabase


    init {
        dialog = Dialog(context)
        dialog.setContentView(R.layout.update_record)

        image  = dialog.findViewById(R.id.image)
        edName     = dialog.findViewById(R.id.upName)
        edColor    = dialog.findViewById(R.id.upColor)
        edPrice   = dialog.findViewById(R.id.upPrice)
        updateBtn  = dialog.findViewById(R.id.updateBtn)
        backBtn  = dialog.findViewById(R.id.backBtn)

        Picasso.get().load(mobiles.image).into(image)
        (edName as TextView).text = mobiles.name
        (edColor as TextView).text = mobiles.color
        (edPrice as TextView).text = mobiles.price

        backBtn.setOnClickListener { dialog.dismiss() }

        updateBtn.setOnClickListener { updateRecord(mobiles.id!!,context) }
        dialog.show()
    }

    private fun updateRecord(id:String,context: Activity) {
            name = edName.text.toString().trim()
            color = edColor.text.toString().trim()
            price = edPrice.text.toString().trim()

        if (name.isEmpty() || color.isEmpty() || price.isEmpty())
        { Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show() }

        else {
            val record = mapOf("name" to name,"color" to color,"price" to price)
            database = FirebaseDatabase.getInstance()
            database.reference.child("mobiles").child(id).updateChildren(record)

                .addOnSuccessListener {
                Toast.makeText(context, "Record updated successfully..", Toast.LENGTH_SHORT).show()
                dialog.dismiss()}

                .addOnFailureListener{
                Toast.makeText(context, "Unable to update", Toast.LENGTH_SHORT).show() }
           }
    }
}