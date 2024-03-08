package com.example.storytales.auth.login

sealed class LoginUiEvent{
    data class EmailChanged(val email:String):LoginUiEvent()
    data class PasswordChanges(val password:String):LoginUiEvent()

    object Login:LoginUiEvent()
    object ForgetPassword:LoginUiEvent()
    object SignUp:LoginUiEvent()
}
