package com.axfex.moneybalance.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.axfex.moneybalance.domain.User.*
import com.google.firebase.auth.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class UserManager(private val sharedPreferences: SharedPreferences, private val context: Context) {
    private val KEY_CURRENT_USER = "currentUser"
    private val KEY_LOGIN_COMPLETE = "loginComplete"
    private val usernamePasswordMap = HashMap<String, String>()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser = MutableLiveData<User>()

    init {
        auth.setLanguageCode(Locale.getDefault().language)
//        val user=auth.currentUser
//        Log.i("UserManager", "UserManager: ${user?.displayName}")
//
//        user.let { currentUser.value=it }
//
//        Log.i("UserManager", "UserManager:  ${Locale.getDefault().language}")
//
//        usernamePasswordMap["Al"] = "123"
//        val current = sharedPreferences.getString(KEY_CURRENT_USER, null)
//        currentUser.apply {
//
//            if (current != null) value = Authenticated(
//                current,
//                sharedPreferences.getBoolean(KEY_LOGIN_COMPLETE, false)
//            )
//            else value = NotAuthenticated
//        }

        auth.currentUser?.let {
            currentUser.value=Authenticated(it,true)
        } ?: run {
            currentUser.value=NotAuthenticated
        }

    }

    suspend fun signIn(credentials: AuthCredential) = suspendCoroutine<SignInResult> { cont ->
        auth.signInWithCredential(credentials).addOnCompleteListener { result ->
            when {
                result.isSuccessful -> {
                    cont.resume(SignInResult.SUCCESSFUL)
                    Log.d("UserManager", "signIn: success!")
                    auth.currentUser?.let {
                        it.sendEmailVerification()
                        it.sendEmailVerification()
                        setCurrentUser(it)
                    }
                }

                else -> {
                    val message=result.exception?.message?:"Sign in error"
                    cont.resume(SignInResult.ERROR(message))
                    Log.i("UserManager", "signIn: ", result.exception )
                }
            }
        }
    }

    suspend fun signInAnonimously() = suspendCoroutine<SignInResult> { cont ->
        auth.signInAnonymously().addOnCompleteListener { result ->
            when {
                result.isSuccessful -> {
                    cont.resume(SignInResult.SUCCESSFUL)
                    Log.d("UserManager", "signUp: success!")
                }

                else -> {
                    val message=result.exception?.message?:"Sign in error"
                    cont.resume(SignInResult.ERROR(message))
                    Log.i("UserManager", "signUp: ", result.exception )
                }
            }
        }
    }



    suspend fun signUp(username:String="User",email:String,password:String) = suspendCoroutine<SignInResult> { cont ->

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { result ->
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

                    val message=result.exception?.message?:"Sign up error"
                    cont.resume(SignInResult.ERROR(message))
                    Log.i("UserManager", "signUp: $email", result.exception )
                }
            }

        }
    }





    private fun setCurrentUser(user: FirebaseUser) {
        sharedPreferences.edit().putString(KEY_CURRENT_USER, user.displayName).apply()
    }



    fun findUser(userName: String): String? {
        Thread.sleep(2000)
        Log.i("LOGIN_API", "findUser: $userName")
        return usernamePasswordMap[userName]
    }


    fun logout() {
        auth.signOut()
//        sharedPreferences.edit().clear().apply()
//        currentUser.value = NotAuthenticated
    }

    fun completeLogin() {
        sharedPreferences.edit().putBoolean(KEY_LOGIN_COMPLETE, true).apply()
    }

    sealed class SignInResult {
        object SUCCESSFUL : SignInResult()
        class ERROR(val errorMessage: String) : SignInResult()
    }


//    suspend fun signIn(credentials: Credentials) = withContext(Dispatchers.IO) {
//
//        when (findUser(userName)) {
//            null -> SignInResult.ERROR("No valid password found")
//            userName -> {
//                sharedPreferences.edit().putString(KEY_CURRENT_USER, userName).apply()
//                SignInResult.SUCCESSFUL
//            }
//            else -> SignInResult.ERROR("Unknown Error")
//        }
//        SignInResult.SUCCESSFUL
//    }

}