package com.axfex.moneybalance.domain

sealed class State {
    object Loading : State()
    object UnknownUserError : State()
    object Successful : State()
}