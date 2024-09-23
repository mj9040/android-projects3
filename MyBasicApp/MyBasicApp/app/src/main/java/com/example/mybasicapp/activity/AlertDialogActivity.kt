package com.example.mybasicapp.activity


import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mybasicapp.R
import com.example.mybasicapp.databinding.ActivityAlertDialogBinding
import com.example.mybasicapp.databinding.CustomAlertDialogBinding

class AlertDialogActivity : AppCompatActivity() {

    lateinit var binding: ActivityAlertDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dialogBtn1.setOnClickListener {
            val  builder:AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setIcon(R.drawable.ic_baseline_arrow_outward_24)
            builder.setTitle("Delete");
            builder.setMessage("Are you sure want to delete")

            builder.setPositiveButton("Ok")
            {
                    dialogInterface,which ->
                Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show() }

            builder.setNegativeButton("Cancel")
            {
                    dialogInterface,which ->
                Toast.makeText(this, "Item not be deleted", Toast.LENGTH_SHORT).show() }

            val alertDialog:AlertDialog = builder.create()
            alertDialog.show()
        }


        binding.dialogBtn2.setOnClickListener {

            val builder  =  AlertDialog.Builder(this)
            builder.setIcon(R.drawable.ic_baseline_arrow_outward_24)
            builder.setTitle("Exit")
            builder.setMessage("Are you sure want to exit")

            builder.setPositiveButton("Yes")
            {
                DialogInterface,it ->
                Toast.makeText(this, "You are Exit", Toast.LENGTH_SHORT).show() }

            builder.setNegativeButton("No")
            {
                DialogInterface,it ->
                Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show() }

            builder.setNeutralButton("Help")
            {
                DialogInterface,it ->
                Toast.makeText(this, "This is Help Screen", Toast.LENGTH_SHORT).show() }

            val alertDialog:AlertDialog = builder.create()
            alertDialog.show()
        }

        binding.dialogBtn3.setOnClickListener {

               val dialog  = Dialog(this)
               dialog.setContentView(R.layout.custom_alert_dialog)
               val okBtn:Button  = dialog.findViewById(R.id.okBtn)

               okBtn.setOnClickListener {
                   Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show()
                   dialog.dismiss()
               }

            dialog.show()

        }

    }
}


