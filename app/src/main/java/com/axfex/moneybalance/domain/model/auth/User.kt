package com.axfex.moneybalance.domain.model.auth

import com.google.firebase.auth.FirebaseUser

sealed class User {
    object NotAuthenticated: User()
    data class Authenticated(val user: FirebaseUser, val loginComplete: Boolean) : User()
}