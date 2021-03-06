package com.axfex.moneybalance.domain.model.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.axfex.moneybalance.data.source.local.UserPrefs
import com.axfex.moneybalance.domain.model.auth.User.*
import com.google.firebase.auth.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class UserManager(private val userPrefs: UserPrefs) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser = MutableLiveData<User>()

    init {
        auth.setLanguageCode(Locale.getDefault().language)

        auth.currentUser?.let {
            currentUser.value=Authenticated(it,true)
        } ?: run {
            currentUser.value=NotAuthenticated
        }

    }

    suspend fun signIn(email: String, password: String) = suspendCoroutine<SignInResult> { cont ->
        val credentials = EmailAuthProvider.getCredential(email, password)

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
        auth.currentUser?.getIdToken(true)?.addOnSuccessListener { token ->
            Log.i("UserManager", "signIn (line 52): ${token.token.toString()}}")

        }


    }

    fun getUser()= auth.currentUser

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
        userPrefs.displayUserName= user.displayName
    }

    fun logout() {
        auth.signOut()
        
        currentUser.value=NotAuthenticated
        userPrefs.displayUserName=""
        userPrefs.isFirstStartComplete=false
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