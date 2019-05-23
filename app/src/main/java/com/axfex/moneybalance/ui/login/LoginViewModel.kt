package com.axfex.moneybalance.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.UserManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.axfex.moneybalance.ui.login.LoginViewModel.LoginViewState.*
import com.axfex.moneybalance.ui.login.LoginViewModel.LogoViewState.*
import com.axfex.moneybalance.ui.login.LoginViewModel.SignModeViewState.*
class LoginViewModel(val userManager: UserManager) : ViewModel(), CoroutineScope {
    val TAG="SignUpViewModel"
    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    val state: MutableLiveData<LoginViewState> = MutableLiveData()
    val logoState: MutableLiveData<LogoViewState> = MutableLiveData()
    val signModeState: MutableLiveData<SignModeViewState> = MutableLiveData()

    fun login(username: String, password: String) = launch {
        state.value = LoginViewLoadingState
        userManager.signIn(username, password).let {
            when (it){
                UserManager.SignInResult.SUCCESSFUL-> {
                    Log.i(TAG, "login: success")
                    state.value=LoginView
                }
                is UserManager.SignInResult.ERROR->{
                    state.value=LoginViewWithErrorState(it.errorMessage)
                }
            }
        }

        Log.i("password complete", "login: ")

    }



    fun signUp(email: String, password: String) = launch {
        state.value = LoginViewLoadingState
        userManager.signUp(email, password).let {
            when (it) {
                UserManager.SignInResult.SUCCESSFUL -> {
                    Log.i(TAG, "login: success")
                    state.value = LoginView
                }
                is UserManager.SignInResult.ERROR -> {
                    state.value = LoginViewWithErrorState(it.errorMessage)
                }
            }
        }

        Log.i("password complete", "login: ")
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

        signModeState.value = SignModeViewState.SignUpView
    }

    fun switchToSignIn() {
        if (state.value == LoginViewLoadingState) return
        if (signModeState.value == SignInView) return

        signModeState.value = SignInView
    }


    sealed class LoginViewState {
        object LoginView : LoginViewState()
        object LoginViewLoadingState : LoginViewState()
        class LoginViewWithErrorState(val errorMessage:String) : LoginViewState()
    }

    sealed class LogoViewState {
        object LogoBigView : LogoViewState()
        object LogoSmallView : LogoViewState()
        object NoLogoView:LogoViewState()
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
