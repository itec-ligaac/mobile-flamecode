package com.flamecode.nomoretime.database

import android.content.Context
import android.content.SharedPreferences
import com.flamecode.nomoretime.model.User

/**
 * For saving easy t
 */
class LocalStorage(private val appContext: Context) {

    /**
     * USER_DATABASE_EXIST -> if user was created on this device
     * USER_DATABASE -> user id
     *
     * User id is they key for his data (analytics, history, preferences etc.)
     */
    companion object {

        const val USER_DATABASE_EXIST = "USER_DATABASE_EXIST"
        const val USER_DATABASE = "USER_DATABASE"
    }

    private lateinit var sharedPreferences: SharedPreferences

    fun getUser(): User? {

        val localClassNameFromActivity = "MainActivity"
        sharedPreferences = appContext.getSharedPreferences(localClassNameFromActivity,
                Context.MODE_PRIVATE)

        val existUserOnThisDevice = sharedPreferences.getBoolean(USER_DATABASE_EXIST, false)

        return if (existUserOnThisDevice) {

            sharedPreferences.getString(USER_DATABASE, "")?.let { User(it) }

        } else {
            null
        }
    }
}