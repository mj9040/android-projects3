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
import com.example.firebasecrudapp.R
import com.example.firebasecrudapp.databinding.FragmentProfileBinding
import com.example.firebasecrudapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var imageUri:Uri

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

         database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database.reference.child("User").child(FirebaseAuth.getInstance().uid!!).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val  user = snapshot.getValue(User::class.java)!!

                Picasso.get().load(user.profile).placeholder(R.drawable.placeholder).into(binding.profile)
                binding.name.text = user.name
                binding.email.text = user.email
                binding.pass.text = user.pass

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

       binding.addProfile.setOnClickListener {
           val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
           galleryImage.launch(intent)
       }
        binding.saveBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            addProfile()
        }
    }

    private val galleryImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode== Activity.RESULT_OK)
        { val data = it.data
            imageUri = data?.data!!
            binding.profile.setImageURI(imageUri) } }

    private fun addProfile() {
       val ref = storage.reference.child("profile").child(FirebaseAuth.getInstance().uid!!)
        ref.putFile(imageUri).addOnSuccessListener{
            ref.downloadUrl.addOnSuccessListener {
                database.reference.child("User").child(FirebaseAuth.getInstance().uid!!).child("profile").setValue(it.toString()).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Profile Added Successfully", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null }
}