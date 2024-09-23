package com.example.firebasecrudapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.firebasecrudapp.R
import com.example.firebasecrudapp.adapter.MobileAdapter
import com.example.firebasecrudapp.databinding.FragmentDashboardBinding
import com.example.firebasecrudapp.dialog.UpdateDialog
import com.example.firebasecrudapp.model.Mobiles
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var adapter: MobileAdapter
    private lateinit var arrayList:ArrayList<Mobiles>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.linearProgressIndicator.visibility = View.VISIBLE
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        arrayList = arrayListOf()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = MobileAdapter(::deleteUser,::updateUser)
        binding.rv.adapter = adapter
        getUserRecord()

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_add)
        }
    }


    private fun getUserRecord()
    {
        binding.linearProgressIndicator.visibility = View.INVISIBLE
        database.reference.child("mobiles")
                .addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot)
            {
                arrayList.clear()
                  for (item in snapshot.children) {
                      val mobiles = item.getValue(Mobiles::class.java)
                      mobiles?.id = item.key
                      arrayList.add(mobiles!!) }

                  adapter.submitList(arrayList)
                  adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            } }) }



    private fun updateUser(mobiles: Mobiles)
    {
        Toast.makeText(context, "updateUser"+mobiles.id.toString(), Toast.LENGTH_SHORT).show()
        UpdateDialog(requireActivity(),mobiles) }


    private fun deleteUser(mobiles: Mobiles)
    {

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setIcon(R.drawable.ic_baseline_delete_24)
        builder.setTitle("Delete")
        builder.setMessage("Are U sure want to delete")

        builder.setPositiveButton("Ok"){_,_ ->

           database.reference.child("mobiles").child(mobiles.id!!).removeValue()
           storage.reference.child("images").child(mobiles.id!!).delete()
               .addOnSuccessListener {
               Toast.makeText(context, "Record deleted successfully", Toast.LENGTH_SHORT).show() }

               .addOnFailureListener {
                   Toast.makeText(context, "Unable to Delete", Toast.LENGTH_SHORT).show() } }

           builder.create().show()
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}