package com.example.tutorial9_savedata

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    companion object {
        const val KEY = "com.example.tutorial9_savedata.SignInActivity.unique"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signin = findViewById<Button>(R.id.signinbtn)
        val uniqueid = findViewById<TextInputEditText>(R.id.uniqueid)


        signin.setOnClickListener {
            val unique = uniqueid.text.toString()

            if(unique.isNotEmpty()) {
                readData(unique)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun readData(uniqueid: String) {
        //To get reference till "Users"
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueid).get().addOnSuccessListener {

            if(it.exists()) {
                val uniqueid = it.child("unique").value

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(KEY,uniqueid.toString())
                startActivity(intent)

            } else {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Please enter valid creditantials", Toast.LENGTH_SHORT).show()
        }
    }
}