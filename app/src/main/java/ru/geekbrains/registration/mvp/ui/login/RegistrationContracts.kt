package ru.geekbrains.registration.mvp.ui.login

import android.os.Handler
import androidx.annotation.MainThread
import ru.geekbrains.registration.mvp.utils.Signer

interface RegistrationContracts {

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
        val shouldShowProgress: Signer<Boolean>
        val isSuccess: Signer<Boolean>
        val error: Signer<String>

        fun onLogin(login: String, password: String)
        fun onCredentialsChange()
    }
}