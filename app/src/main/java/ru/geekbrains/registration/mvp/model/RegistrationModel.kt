package ru.geekbrains.registration.mvp.model

import ru.geekbrains.registration.mvp.utils.FakeServer

class RegistrationModel {

    private var server: FakeServer = FakeServer()
    private var answer: String = ""

    fun checkUser (login: String, password: String): String {
        answer = server.checkUser(login, password)
        return answer
    }
}