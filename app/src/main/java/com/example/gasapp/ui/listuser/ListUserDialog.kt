package com.example.gasapp.ui.listuser

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gasapp.databinding.DialogUsersBinding

class ListUserDialog(private val context: Context, lifecycleOwner: LifecycleOwner) {
    private lateinit var dialog: Dialog
    private val binding: DialogUsersBinding =
        DialogUsersBinding.inflate((context as Activity).layoutInflater)
    private var viewModel: ListUserViewModel = ListUserViewModel(context)

    init {
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewUsers.setHasFixedSize(true)
        viewModel.getListUsers()
        viewModel.listUserMutableLiveData.observe(lifecycleOwner){list->
            binding.recyclerViewUsers.adapter = AdapterListUser(context, list)
            showDialog()
        }
    }

    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }
}