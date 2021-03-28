package com.flamecode.nomoretime.database

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.flamecode.nomoretime.model.User
import com.flamecode.nomoretime.util.getRandomString

/**
 * For saving easy the data in local storage (SharedPreference)
 *
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
        const val SIZE_USER_ID = 10
    }

    private lateinit var sharedPreferences: SharedPreferences

    fun getUser(): User? {

        initSharedPreference()

        val existUserOnThisDevice = sharedPreferences.getBoolean(USER_DATABASE_EXIST, false)

        return if (existUserOnThisDevice) {

            sharedPreferences.getString(USER_DATABASE, "")?.let { User(it) }

        } else {

            null
        }
    }

    fun setUserInterest(interest : String) {

        initSharedPreference()
        val sharedPrefEditor = sharedPreferences.edit()

        val mutableSet = getUserListOfInterests()
        mutableSet?.add(interest)
        sharedPrefEditor.putStringSet(getUser()?.id + "interest", mutableSet)
        sharedPrefEditor.apply()

        Log.d("getUserListOfInterests", "" + sharedPreferences.getStringSet(getUser()?.id+ "interest", mutableSetOf()))
    }

    private fun getUserListOfInterests() : MutableSet<String>? {

        initSharedPreference()
        return sharedPreferences.getStringSet(getUser()?.id + "interest", mutableSetOf())
    }

    fun createUser() : User {

        initSharedPreference()
        val sharedPrefEditor = sharedPreferences.edit()

        sharedPrefEditor.putBoolean(USER_DATABASE_EXIST, true)

        val newId = getRandomString(SIZE_USER_ID)
        sharedPrefEditor.putString(USER_DATABASE, newId)

        sharedPrefEditor.apply()

        return User(id = newId)
    }

    private fun initSharedPreference() {

        val localClassNameFromActivity = "MainActivity"
        sharedPreferences = appContext.getSharedPreferences(localClassNameFromActivity,
                Context.MODE_PRIVATE)
    }
}