package com.example.ktfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ktfood.R
import com.example.ktfood.databinding.ActivitySignBinding
import com.example.ktfood.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var userName : String
    private lateinit var email : String
    private lateinit var passWord : String
    private lateinit var dataBase : DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient


    private val binding:ActivitySignBinding by lazy {
        ActivitySignBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //inittialize
        auth = Firebase.auth
        dataBase = Firebase.database.reference


//        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
//        googleSignInClient = GoogleSignIn.getClient(this, )

        binding.signbutton.setOnClickListener {

            userName = binding.editTextText.text.toString().trim()
            email = binding.editTextTextEmailAddress.text.toString().trim()
            passWord = binding.editTextTextPassword.text.toString().trim()

            if(userName.isBlank() || email.isEmpty() || passWord.isBlank()){
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            }else {
                createAccount(email, passWord)
            }

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        binding.haveAcc.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createAccount(email: String, passWord: String) {
        auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener{
                task ->
            if(task.isSuccessful){
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    saveUserData()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
            }else{
                    Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                    Log.d("Account" , "createAccount : Failure" , task.exception )
            }
        }
    }

    private fun saveUserData() {
        userName = binding.editTextText.text.toString().trim()
        email = binding.editTextTextEmailAddress.text.toString().trim()
        passWord = binding.editTextTextPassword.text.toString().trim()

        val user = UserModel(userName, email, passWord)
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        dataBase.child("user").child(userID).setValue(user)
    }
}