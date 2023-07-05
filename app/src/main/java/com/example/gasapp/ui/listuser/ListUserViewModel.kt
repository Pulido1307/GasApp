package com.example.gasapp.ui.listuser

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gasapp.models.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListUserViewModel(private val context: Context) : ViewModel() {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Users")
    val listUserMutableLiveData = MutableLiveData<ArrayList<Users>>()
    fun getListUsers() {
        val listUsers = arrayListOf<Users>()
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    listUsers.clear()
                    for (user in snapshot.children) {
                        listUsers.add(user.getValue(Users::class.java)!!)
                    }

                    listUserMutableLiveData.postValue(listUsers)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error ${error}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}