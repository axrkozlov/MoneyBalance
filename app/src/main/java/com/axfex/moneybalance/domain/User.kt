package com.axfex.moneybalance.domain

sealed class User {
    object NotAuthenticated : User()
    data class AuthenticatedUser(val username: String, val loginComplete: Boolean) : User()
}