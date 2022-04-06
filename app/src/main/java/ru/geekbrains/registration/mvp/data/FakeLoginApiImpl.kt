package ru.geekbrains.registration.mvp.data

import ru.geekbrains.registration.mvp.domain.LoginApi
import ru.geekbrains.registration.mvp.utils.FakeServer

class FakeLoginApiImpl: LoginApi {
    private var server: FakeServer = FakeServer()
    private var answer: String = ""

    override fun login(login: String, password: String): String {
        Thread.sleep(1500)
            answer = server.checkUser(login, password)
            return answer
    }

    override fun register(
        login: String,
        password: String,
        checkPassword: String,
        eMail: String,
        date: String
    ): Boolean {
        Thread.sleep(1500)
        return true
    }

    override fun restorePassword(login: String): Boolean {
        Thread.sleep(1500)
        return true
    }

    override fun logout(): Boolean {
        Thread.sleep(1500)
        return true
    }
}