package com.udacity.shoestore.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _emptyCheck = MutableLiveData<Boolean>()
    val emptyCheck: LiveData<Boolean>
        get() = _emptyCheck


    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        email.value = ""
        password.value = ""
        _emptyCheck.value = true
    }

    fun signIn(email : String , password : String){
        _emptyCheck.value = email.isEmpty() || password.isEmpty()
    }

    fun login(email : String , password : String){
        _emptyCheck.value = email.isEmpty() || password.isEmpty()
    }

}