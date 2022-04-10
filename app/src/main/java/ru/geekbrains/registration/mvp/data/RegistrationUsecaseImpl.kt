package ru.geekbrains.registration.mvp.data

import android.os.Handler
import ru.geekbrains.registration.mvp.domain.LoginApi
import ru.geekbrains.registration.mvp.domain.RegistrationUsecase

class RegistrationUsecaseImpl(private val api: LoginApi) :
    RegistrationUsecase {
    override fun login(login: String, password: String, callback: (String) -> Unit) {
        Thread {
            val result = api.login(login, password)
            callback(result)
        }.start()
    }
}