package ru.geekbrains.registration.mvp

import android.os.Handler
import androidx.annotation.MainThread

class RegistrationContracts {

    interface View {
        @MainThread
        fun setSuccess()
        @MainThread
        fun setError(error: String)
        @MainThread
        fun showProgress()
        @MainThread
        fun hideProgress()
        fun showLogin()
        fun showPassword()
        fun getHandler(): Handler
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChange()
    }
}