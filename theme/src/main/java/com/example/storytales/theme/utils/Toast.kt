package com.example.storytales.theme.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

@RequiresApi(Build.VERSION_CODES.R)
private fun Context.showToast(message: String, onToastDisplayChange: (Boolean) -> Unit) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).also {
        it.addCallback(@RequiresApi(Build.VERSION_CODES.R)
        object : Toast.Callback(){
            override fun onToastHidden() {
                super.onToastHidden()
                onToastDisplayChange(false)
            }

            override fun onToastShown() {
                super.onToastShown()
                onToastDisplayChange(true)
            }
        })
    }.show()
}

private fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(message: String,onToastDisplayChange: (Boolean) -> Unit = {}){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        showToast(message,onToastDisplayChange)
    }else{
        showToast(message)
    }
}

fun Context.toast(@StringRes message: Int,onToastDisplayChange: (Boolean) -> Unit = {}){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        showToast(getString(message),onToastDisplayChange)
    }else{
        showToast(getString(message))
    }
}