package com.axfex.moneybalance.ui.signin

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.UserManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.axfex.moneybalance.ui.signin.SignInViewModel.LoginViewState.*
import com.axfex.moneybalance.ui.signin.SignInViewModel.LogoViewState.*
import com.axfex.moneybalance.ui.signin.SignInViewModel.SignModeViewState.*
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseUser


class SignInViewModel(val userManager: UserManager) : ViewModel(), CoroutineScope {
    val TAG = "SignUpViewModel"
    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    val state: MutableLiveData<LoginViewState> = MutableLiveData()
    val logoState: MutableLiveData<LogoViewState> = MutableLiveData()
    val signModeState: MutableLiveData<SignModeViewState> = MutableLiveData()

    fun signIn(email: String,password: String) = launch {
        if (checkIsEmpty(email, password)) return@launch
        state.value = LoginViewLoadingState
        val credentials = EmailAuthProvider.getCredential(email, password)
        userManager.signIn(credentials).let {
            when (it) {
                UserManager.SignInResult.SUCCESSFUL -> {
                    state.value = LoginViewIdle
                }
                is UserManager.SignInResult.ERROR -> {
                    state.value = LoginViewWithErrorState(it.errorMessage)
                }
            }
        }
    }

    fun signUp(username:String,email: String, password: String) = launch {
        if (checkIsEmpty(email, password)) return@launch
        state.value = LoginViewLoadingState
        userManager.signUp(username,email,password).let {
            when (it) {
                UserManager.SignInResult.SUCCESSFUL -> {
                    state.value = LoginViewIdle
                }
                is UserManager.SignInResult.ERROR -> {
                    state.value = LoginViewWithErrorState(it.errorMessage)
                }
            }
        }
    }

    private fun checkIsEmpty(email: String, password: String) = run {
        when {
            TextUtils.isEmpty(email) -> {
                state.value=LoginViewEmailRequiredState
                true
            }
            TextUtils.isEmpty(password) -> {
                state.value=LoginViewPasswordRequiredState
                true
            } else -> false
        }
    }




    fun forgotPassword() {

    }

    suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }

    /**States*/

    fun beginTyping() {
        if (state.value == LoginViewLoadingState) return
        if (logoState.value == LogoSmallView) return

        logoState.value = LogoSmallView
    }

    fun finishTyping() {
        if (state.value == LoginViewLoadingState) return
        if (logoState.value == LogoBigView) return
        logoState.value = LogoBigView
    }

    fun switchToSignUp() {
        if (state.value == LoginViewLoadingState) return
        if (signModeState.value == SignModeViewState.SignUpView) return
        state.value=LoginViewIdle
        signModeState.value = SignModeViewState.SignUpView
    }

    fun switchToSignIn() {
        if (state.value == LoginViewLoadingState) return
        if (signModeState.value == SignInView) return
        state.value=LoginViewIdle
        signModeState.value = SignInView
    }


    sealed class LoginViewState {
        object LoginViewIdle : LoginViewState()
        object LoginViewLoadingState : LoginViewState()
        class LoginViewSignSuccess(val user:FirebaseUser):LoginViewState()
        object LoginViewEmailRequiredState : LoginViewState()
        object LoginViewPasswordRequiredState : LoginViewState()
        class LoginViewWithErrorState(val errorMessage: String) : LoginViewState()
    }

    sealed class LogoViewState {
        object LogoBigView : LogoViewState()
        object LogoSmallView : LogoViewState()
    }

    sealed class SignModeViewState {
        object SignInView : SignModeViewState()
        object SignUpView : SignModeViewState()
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }


}
