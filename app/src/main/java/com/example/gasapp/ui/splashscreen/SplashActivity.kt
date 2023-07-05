package com.example.gasapp.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.gasapp.R
import com.example.gasapp.databinding.ActivitySplashBinding
import com.example.gasapp.ui.adduser.AddUserViewModel
import com.example.gasapp.ui.listuser.ListUserDialog
import com.example.gasapp.ui.main.MainActivity
import com.example.gasapp.utils.RotateImage
import com.example.gasapp.utils.SharedPreferencesHelper

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        RotateImage.rotateImage(binding.imageViewLoading)
        Handler(Looper.getMainLooper()).postDelayed({
            if (validateSession()) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val dialog = ListUserDialog(this@SplashActivity, this)
            }
        }, 3000)
    }

    private fun validateSession(): Boolean {
        return SharedPreferencesHelper(this).getData("userName").isNotEmpty()
    }
}
