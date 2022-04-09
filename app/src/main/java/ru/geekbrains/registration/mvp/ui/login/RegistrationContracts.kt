package ru.geekbrains.registration.mvp.ui.login

import android.os.Handler
import androidx.annotation.MainThread

class RegistrationContracts {

//    interface View {
//        @MainThread
//        fun setSuccess()
//
//        @MainThread
//        fun setError(error: String)
//
//        @MainThread
//        fun showProgress()
//
//        @MainThread
//        fun hideProgress()
//        fun showLogin()
//        fun showPassword()
//        fun getHandler(): Handler
//    }

    interface Presenter {
        var shouldShowProgress: Boolean
        var isSuccess: Boolean
        var error: String?

        fun onLogin(login: String, password: String)
        fun onCredentialsChange()
    }
}