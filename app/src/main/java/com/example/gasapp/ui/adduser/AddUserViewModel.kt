package com.example.gasapp.ui.adduser

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.gasapp.models.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddUserViewModel : ViewModel() {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Users")

    fun addUser(userName: String, context: Context) {
        val idPush = dataBase.push().key
        val user = Users(userName, idPush)
        dataBase.child(idPush!!).setValue(user).addOnCompleteListener {
            Toast.makeText(context, "Registrado!!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Fallo el registro", Toast.LENGTH_LONG).show()
        }
    }

}