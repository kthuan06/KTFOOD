package com.example.ktfood.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ktfood.databinding.FragmentProfileBinding
import com.example.ktfood.model.UserModel
import com.example.ktfood.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfileFragment : Fragment() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        setUserData()
        binding.button7.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val address = binding.editAddress.text.toString()
            val phone = binding.editPhone.text.toString()

            updateUserData(name, email, address, phone)

        }
        binding.apply {
            editName.isEnabled = false
            editAddress.isEnabled = false
            editEmail.isEnabled = false
            editPhone.isEnabled = false
        }
        binding.button6.setOnClickListener {
            binding.apply {
                editName.isEnabled = ! editName.isEnabled
                editAddress.isEnabled = !editAddress.isEnabled
                editEmail.isEnabled = !editEmail.isEnabled
                editPhone.isEnabled = !editPhone.isEnabled
            }
        }
        binding.button8.setOnClickListener {
            val intent =Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
        return binding.root

    }

    private fun updateUserData(name: String, email: String, address: String, phone: String) {
        val userID = auth.currentUser?.uid
        if(userID != null){
            val userReference = database.getReference("user").child(userID)
            val userData = hashMapOf(
                "name" to name,
                "email" to email,
                "address" to address,
                "phone" to phone
                )
            userReference.setValue(userData).addOnSuccessListener {
                Toast.makeText(requireContext(), "Update data successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Update data failed", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun setUserData() {
        val userID = auth.currentUser?.uid
        if(userID!= null){
            val userReference = database.getReference("user").child(userID)

            userReference.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val userProfile = snapshot.getValue(UserModel::class.java)
                        if(userProfile!= null){
                            binding.editName.setText(userProfile.name)
                            binding.editEmail.setText(userProfile.email)
                            binding.editAddress.setText(userProfile.address)
                            binding.editPhone.setText(userProfile.phone)

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }


}