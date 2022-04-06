package ru.geekbrains.registration.mvp

import ru.geekbrains.registration.mvp.utils.FakeServer

class FakeLoginApiImpl: LoginApi {
    private var server: FakeServer = FakeServer()
    private var answer: String = ""

    override fun login(login: String, password: String): String {
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
        TODO("Not yet implemented")
    }

    override fun restorePassword(login: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}