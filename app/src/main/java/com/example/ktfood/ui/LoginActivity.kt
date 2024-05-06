package com.example.ktfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ktfood.R
import com.example.ktfood.databinding.ActivityLoginBinding
import com.example.ktfood.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private var userName : String? =null
    private lateinit var email : String
    private lateinit var passWord : String
    private lateinit var dataBase : DatabaseReference

    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //init
        auth = Firebase.auth
        dataBase = FirebaseDatabase.getInstance().reference



        binding.loginbutton.setOnClickListener{

            email = binding.editTextTextEmailAddress.text.toString().trim()
            passWord = binding.editTextTextPassword.text.toString().trim()

            if(email.isBlank() || passWord.isBlank()){
                Toast.makeText(this, "Fill all", Toast.LENGTH_SHORT).show()
            }else{
                createUser(email, passWord)
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            }


        }
        binding.donotAcc.setOnClickListener{
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser(email: String, passWord: String) {
        auth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val user = auth.currentUser
                updateUi(user)
            }else{
                auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        savaUserData()
                        val user = auth.currentUser
                        updateUi(user)
                    }else{
                        Toast.makeText(this, "Login in failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun savaUserData() {
        email = binding.editTextTextEmailAddress.text.toString().trim()
        passWord = binding.editTextTextPassword.text.toString().trim()

        val user = UserModel(userName ,email,passWord)
        val userID = FirebaseAuth.getInstance().currentUser!!.uid

        //
        dataBase.child("user").child(userID).setValue(user)

    }

    private fun updateUi(user: FirebaseUser?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}