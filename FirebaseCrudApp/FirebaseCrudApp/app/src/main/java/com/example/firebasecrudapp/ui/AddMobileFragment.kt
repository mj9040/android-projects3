package com.example.firebasecrudapp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebasecrudapp.R
import com.example.firebasecrudapp.databinding.FragmentAddMobileBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddMobileFragment : Fragment() {

    private var _binding: FragmentAddMobileBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var storage: FirebaseStorage

    private lateinit var imageUri: Uri
    private lateinit var name:String
    private lateinit var color:String
    private lateinit var price:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentAddMobileBinding.inflate(inflater, container, false)

        firebaseDatabase = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.choosePicText.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            galleryImage.launch(intent) }

        binding.saveBtn.setOnClickListener {
            validateInput() }
    }


    private val galleryImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode== Activity.RESULT_OK)
        { val data = it.data
            imageUri = data?.data!!
//            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
//            binding.edImg.setImageBitmap(bitmap)
            binding.edImg.setImageURI(imageUri) } }

    private fun validateInput() {
        name   = binding.edName.text.toString().trim()
        color  = binding.edColor.text.toString().trim()
        price   = binding.edPrice.text.toString().trim()

        if (name.isEmpty() || color.isEmpty() || price.isEmpty())
        {   Toast.makeText(requireContext(), "Please provide the credentials", Toast.LENGTH_SHORT).show()  }

        else if (binding.edImg.drawable==null)
        {   Toast.makeText(requireContext(), "Please Select Image", Toast.LENGTH_SHORT).show()  }

        else  {
            binding.linearProgressIndicator.visibility = View.VISIBLE
            insertMobileRecord()
        }
    }

    private fun insertMobileRecord() {
        val mobileData = HashMap<String,String>()
        mobileData["name"] = name
        mobileData["color"] = color
        mobileData["price"] = price

        val dbRef = firebaseDatabase.reference.child("mobiles").push()
        dbRef.setValue(mobileData).addOnSuccessListener{
            val id = dbRef.key!!

            val storageRef = storage.reference.child("images").child(id)
            storageRef.putFile(imageUri).addOnSuccessListener{
                storageRef.downloadUrl.addOnSuccessListener {

                    dbRef.child("image").setValue(it.toString()).addOnSuccessListener {
                        binding.linearProgressIndicator.visibility = View.INVISIBLE
                        binding.txtError.text=""
                        Toast.makeText(requireContext(), "Record inserted successfully..", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_add_to_dashboard)

                    }.addOnFailureListener { error->
                        binding.linearProgressIndicator.visibility = View.INVISIBLE
                        binding.txtError.text = error.message }
                } }

        }.addOnFailureListener {
            binding.linearProgressIndicator.visibility = View.INVISIBLE
            binding.txtError.text = it.message
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}