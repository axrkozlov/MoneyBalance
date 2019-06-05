package com.axfex.moneybalance.ui.start.signin

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.auth.UserManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.SigninViewState.*
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.TypingViewState.*
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.SignModeViewState.*


class SignInViewModel(val userManager: UserManager) : ViewModel(), CoroutineScope {

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    val signinState: MutableLiveData<SigninViewState> = MutableLiveData()
    val typingState: MutableLiveData<TypingViewState> = MutableLiveData()
    val signModeState: MutableLiveData<SignModeViewState> = MutableLiveData()

    fun signIn(email: String, password: String) = launch {
        if (isEmpty(email, password)) return@launch
        signinState.value = Loading

        userManager.signIn(email, password).let {
            when (it) {
                UserManager.SignInResult.SUCCESSFUL -> {
                    signinState.value = Default
                }
                is UserManager.SignInResult.ERROR -> {
                    signinState.value = Error(it.errorMessage)
                }
            }
        }
    }

    fun signUp(username: String, email: String, password: String) = launch {
        if (isEmpty(email, password)) return@launch
        signinState.value = Loading
        userManager.signUp(username, email, password).let {
            when (it) {
                UserManager.SignInResult.SUCCESSFUL -> {
                    signinState.value = Default
                }
                is UserManager.SignInResult.ERROR -> {
                    signinState.value = Error(it.errorMessage)
                }
            }
        }
    }

    private fun isEmpty(email: String, password: String) = run {
        when {
            TextUtils.isEmpty(email) -> {
                signinState.value = EmailRequired
                true
            }
            TextUtils.isEmpty(password) -> {
                signinState.value = PasswordRequired
                true
            }
            else -> false
        }
    }


    fun forgotPassword() {

    }

    /**States*/
    fun beginTyping() {
        if (signinState.value == Loading) return
        if (typingState.value == Typing) return

        typingState.value = Typing
    }

    fun finishTyping() {
        if (signinState.value == Loading) return
        if (typingState.value == Idle) return

        typingState.value = Idle
    }

    fun switchToSignUp() {
        if (signinState.value == Loading) return
        if (signModeState.value == SignUp) return
        signModeState.value = SignUp

    }

    fun switchToSignIn() {
        if (signinState.value == Loading) return
        if (signModeState.value == SignIn) return
        signModeState.value = SignIn
    }


    sealed class SigninViewState {
        object Default : SigninViewState()
        object Loading : SigninViewState()
        object SigninSuccess : SigninViewState()
        object EmailRequired : SigninViewState()
        object PasswordRequired : SigninViewState()
        class Error(val errorMessage: String) : SigninViewState()
    }

    sealed class TypingViewState {
        object Idle : TypingViewState()
        object Typing : TypingViewState()
    }

    sealed class SignModeViewState {
        object SignIn : SignModeViewState()
        object SignUp : SignModeViewState()
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    fun signInAnonimously() {
    }


}
