package ru.geekbrains.registration.mvp.utils

import ru.geekbrains.registration.mvp.data.Users

class FakeServer {
    private var users: Users = Users()
    private var userName = users.userName
    private var userPassword = users.userPassword
    private var answer: String = ""

    fun checkUser(login: String, password: String): String {
        if (login==userName) {
            if (password==userPassword) {
                answer = "Успех!"
            } else {
                answer = "Неверный пароль."
            }
        } else {
            answer = "Такого пользователя не существует."
        }
        return answer
    }
}