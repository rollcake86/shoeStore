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


    private val _email = MutableLiveData<String>()
    val email : LiveData<String>
     get() = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String>
        get() = _password

    init {
        _email.value = ""
        _password.value = ""
        _emptyCheck.value = true
    }

    fun signIn(email : String , password : String){
        if (email.isEmpty() || password.isEmpty()) {
            _emptyCheck.value = true
        } else {
            _emptyCheck.value = false

        }
    }

    fun login(email : String , password : String){
        if (email.isEmpty() || password.isEmpty()) {
            _emptyCheck.value = true
        } else {
            _emptyCheck.value = false
            Timber.i("login $email , $password" )
        }
    }

}