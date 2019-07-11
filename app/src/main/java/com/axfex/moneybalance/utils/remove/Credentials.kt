package com.axfex.moneybalance.utils.remove
//import android.text.TextUtils
//import android.util.Patterns
//import com.axfex.moneybalance.utils.delete.Credentials.ValidationResult.*
//
//data class Credentials(val email:String, val password:String){
//
//    fun validate(): ValidationResult = run {
//        when{
//            TextUtils.isEmpty(email)-> InvalidEmailEmpty
//            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> InvalidEmailFormat
//            TextUtils.isEmpty(password)-> InvalidPasswordEmpty
//            password.length<6-> InvalidPasswordLength
//            else -> return Valid
//        }
//    }
//
//    sealed class ValidationResult{
//        object Valid: ValidationResult()
//        object InvalidEmailEmpty: ValidationResult()
//        object InvalidPasswordEmpty: ValidationResult()
//        object InvalidEmailFormat: ValidationResult()
//        object InvalidPasswordLength: ValidationResult()
//    }
//
//}