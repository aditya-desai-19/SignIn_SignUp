package com.example.tutorial9_savedata

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupbtn = findViewById<Button>(R.id.signupbtn)
        val uniqueid = findViewById<TextInputEditText>(R.id.uniqueid)
        val email = findViewById<TextInputEditText>(R.id.email)
        val password = findViewById<TextInputEditText>(R.id.password)

        signupbtn.setOnClickListener {
            val unique = uniqueid.text.toString()
            val mail = email.text.toString()
            val pass = password.text.toString()

            val user = User(unique,mail, pass)

            database = FirebaseDatabase.getInstance().getReference("Users")
            if(unique != "" && mail != "" && pass != "") {
                database.child(unique).setValue(user).addOnSuccessListener {
                    uniqueid.text?.clear()
                    email.text?.clear()
                    password.text?.clear()
                    Toast.makeText(this,"User registered successfully",Toast.LENGTH_SHORT).show()
                }
            }


        }

        val navigate = findViewById<Button>(R.id.navigatebtn)

        navigate.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

}