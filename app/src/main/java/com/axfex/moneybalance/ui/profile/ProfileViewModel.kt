package com.axfex.moneybalance.ui.profile

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.domain.UserManager

class ProfileViewModel(val userManager: UserManager) : ViewModel() {

    fun logOut(){
        userManager.logout()
    }
}
