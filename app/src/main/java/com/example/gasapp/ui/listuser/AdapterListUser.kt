package com.example.gasapp.ui.listuser

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gasapp.R
import com.example.gasapp.databinding.ItemUserBinding
import com.example.gasapp.models.Users
import com.example.gasapp.ui.main.MainActivity
import com.example.gasapp.utils.SelectionCard
import com.example.gasapp.utils.SharedPreferencesHelper

class AdapterListUser(private val context: Context, private val listUser: ArrayList<Users>) :
    RecyclerView.Adapter<AdapterListUser.ViewHolderUser>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolderUser(itemView)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        holder.binding.textViewUser.text = listUser[position].nameUser
        holder.binding.cardViewItem.setOnClickListener {
            SelectionCard.selectionItem(holder.binding.cardViewItem, context)
            writeSession(listUser[position])
        }
    }

    private fun writeSession(user: Users) {
        SharedPreferencesHelper(context).addPreference("userName", user.nameUser!!)
        goToActivity()
    }

    private fun goToActivity() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
    }

    inner class ViewHolderUser(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserBinding.bind(view)
    }
}