package com.axfex.moneybalance.utils.remove

//import com.axfex.moneybalance.utils.ValidationResult.*

//val EmailAuthCredential.builder
//get() = EmailAuthCredentialBuilder()
//
//    class EmailAuthCredentialBuilder{

//    inline fun EmailAuthCredential.checkAndBuild(email: String, password: String): ValidationResult = run {
//        when {
//            TextUtils.isEmpty(email) -> InvalidEmailEmpty
//            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> InvalidEmailFormat
//            TextUtils.isEmpty(password) -> InvalidPasswordEmpty
//            password.length < 6 -> InvalidPasswordLength
//            else -> Valid(EmailAuthProvider.getCredential(email, password))
//        }
//    }
//
//
//    sealed class ValidationResult {
//        class Valid(credential: AuthCredential) : ValidationResult()
//        object InvalidEmailEmpty : ValidationResult()
//        object InvalidPasswordEmpty : ValidationResult()
//        object InvalidEmailFormat : ValidationResult()
//        object InvalidPasswordLength : ValidationResult()
//    }
