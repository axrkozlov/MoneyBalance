package com.axfex.moneybalance.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.UserManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.axfex.moneybalance.ui.login.SignUpViewModel.SignUpViewState.*

class SignUpViewModel(val userManager: UserManager) : ViewModel(), CoroutineScope {
    val TAG="SignUpViewModel"
    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    val state: MutableLiveData<SignUpViewState> = MutableLiveData()

    fun login(username: String, password: String) = launch {
        state.value = SignUpViewLoadingState
        userManager.signIn(username, password).let {
            when (it){
                UserManager.SignInResult.SUCCESSFUL-> {
                    Log.i(TAG, "login: success")
                    state.value=SignUpViewIdle
                }
                is UserManager.SignInResult.ERROR->{
                    state.value=SignUpViewWithErrorState(it.errorMessage)
                }
            }
        }

        Log.i("password complete", "login: ")

    }



    fun signUp() {
        launch(
            Dispatchers.Main +
                    Job() +
                    CoroutineName("HelloCoroutine") +
                    CoroutineExceptionHandler { _, _ -> /* ... */ }
        ) {
            /* ... */
        }
    }

    fun forgotPassword() {

    }

    suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }

    /**States*/

    fun beginTyping() {
        if (state.value == SignUpViewTyping) return
        if (state.value == SignUpViewLoadingState) return

        state.value = SignUpViewTyping

    }

    fun finishTyping() {
        if (state.value == SignUpViewLoadingState) return
        state.value = SignUpViewIdle

    }


    override fun onCleared() {
        super.onCleared()
        cancel()
    }


    sealed class SignUpViewState {
        object SignUpViewIdle : SignUpViewState()
        object SignUpViewTyping : SignUpViewState()
        object SignUpViewLoadingState : SignUpViewState()
        class SignUpViewWithErrorState(val errorMessage:String) : SignUpViewState()
    }

}
