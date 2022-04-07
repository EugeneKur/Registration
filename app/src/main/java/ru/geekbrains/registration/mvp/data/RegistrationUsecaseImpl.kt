package ru.geekbrains.registration.mvp.data

import android.os.Handler
import ru.geekbrains.registration.mvp.domain.LoginApi
import ru.geekbrains.registration.mvp.domain.RegistrationUsecase

class RegistrationUsecaseImpl(private val api: LoginApi,private val uiHandler: Handler) : RegistrationUsecase {
    override fun login(login: String, password: String, callback: (String) -> Unit) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}