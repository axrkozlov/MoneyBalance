package com.axfex.moneybalance.domain.model.auth

sealed class State {
    object Loading : State()
    object UnknownUserError : State()
    object Successful : State()
}