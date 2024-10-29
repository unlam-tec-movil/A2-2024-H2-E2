package ar.edu.unlam.mobile.scaffolding.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterScreenViewModel: ViewModel() {

    private val _nameUser= MutableLiveData<String>()
    val nameUser: LiveData<String> = _nameUser

private val _email= MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password= MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _registerEnable= MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable


    fun onRegisterChanged(email:String,password:String,name:String){
        _nameUser.value= name
        _email.value = email
        _password.value = password
        _registerEnable.value=isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6
    fun onRegisterSelected() {

    }



}

    private fun isValidEmail(email: String): Boolean =Patterns.EMAIL_ADDRESS.matcher(email).matches()



