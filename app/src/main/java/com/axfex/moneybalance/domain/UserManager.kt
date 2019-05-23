package com.axfex.moneybalance.domain

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.axfex.moneybalance.domain.User.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class UserManager(private val sharedPreferences: SharedPreferences) {
    private val KEY_CURRENT_USER = "currentUser"
    private val KEY_LOGIN_COMPLETE = "loginComplete"
    private val usernamePasswordMap = HashMap<String, String>()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser = MutableLiveData<User>()

    init {
        usernamePasswordMap["Al"] = "123"
        val current = sharedPreferences.getString(KEY_CURRENT_USER, null)
        currentUser.apply {
            if (current != null) value = AuthenticatedUser(
                current,
                sharedPreferences.getBoolean(KEY_LOGIN_COMPLETE, false)
            )
            else value = NotAuthenticated
        }
    }

    suspend fun signUp(email: String, password: String) = suspendCoroutine<SignInResult> { cont ->
auth.languageCode
        val task = auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            when {
                result.isSuccessful -> {
                    cont.resume(SignInResult.SUCCESSFUL)
                    Log.d("UserManager", "signUp: success!")
                    auth.currentUser?.let {
                        it.sendEmailVerification()

                        setCurrentUser(it)
                    }
                }
                else -> {

                    Log.w("UserManager", "signUp: ", result.exception)
                    cont.resume(SignInResult.ERROR("$result.exception"))
                }
            }

        }
    }


//
//            val task=auth.createUserWithEmailAndPassword(email,password)
//
//            task.addOnCompleteListener { result ->
//                when {
//                    result.isSuccessful -> {
//                        Log.d("UserManager", "signUp: success!")
//                        auth.currentUser?.let {
//                            setCurrentUser(it)
//
//                        }
//
//                       return@lit
//                    }
//                    else -> {
//                        Log.w("UserManager", "signUp: ", result.exception)
//                        SignInResult.ERROR("$result.exception")
//                    }
//                }
//
//            }
//
//
//    }

    private fun setCurrentUser(user: FirebaseUser) {
        sharedPreferences.edit().putString(KEY_CURRENT_USER, user.displayName).apply()
    }


    suspend fun signIn(userName: String, password: String) = withContext(Dispatchers.IO) {
        when (findUser(userName)) {
            null -> SignInResult.ERROR("No valid password found")
            userName -> {
                sharedPreferences.edit().putString(KEY_CURRENT_USER, userName).apply()
                SignInResult.SUCCESSFUL
            }
            else -> SignInResult.ERROR("Unknown Error")
        }

    }

    fun findUser(userName: String): String? {
        Thread.sleep(2000)
        Log.i("LOGIN_API", "findUser: $userName")
        return usernamePasswordMap[userName]
    }


    fun logout() {
        sharedPreferences.edit().clear().apply()
        currentUser.value = NotAuthenticated
    }

    fun completeLogin() {
        sharedPreferences.edit().putBoolean(KEY_LOGIN_COMPLETE, true).apply()
    }

    sealed class SignInResult {
        object SUCCESSFUL : SignInResult()
        class ERROR(val errorMessage: String) : SignInResult()
    }

}