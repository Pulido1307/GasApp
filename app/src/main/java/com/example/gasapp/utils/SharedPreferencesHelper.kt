package com.example.gasapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.io.IOException
import java.security.GeneralSecurityException

class SharedPreferencesHelper(private val context: Context) {
    private val NAME_DATA: String = "com.polarindustries.gasapp.user"
    private lateinit var sharedPref: SharedPreferences

    init {
        var key = getKey()
        try {
            sharedPref = EncryptedSharedPreferences.create(
                NAME_DATA,
                key!!,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            sharedPref = context.getSharedPreferences(NAME_DATA, Context.MODE_PRIVATE)
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getKey(): String? {
        var key: String? = null
        try {
            key = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return key!!
    }

    public fun deletePreferences() {
        sharedPref.edit().clear().commit()
    }

    public fun addPreference(nameData: String, valueData: String) {
        sharedPref.edit().putString(nameData, valueData).apply()
    }

    public fun getData(nameData: String): String {
        return sharedPref.getString(nameData, "")!!
    }
}