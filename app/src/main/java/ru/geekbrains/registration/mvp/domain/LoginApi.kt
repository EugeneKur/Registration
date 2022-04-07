package ru.geekbrains.registration.mvp.domain

interface LoginApi {
    fun login(login: String, password: String): String
    fun register(
        login: String,
        password: String,
        checkPassword: String,
        eMail: String,
        date: String
    ): Boolean

    fun restorePassword(login: String): Boolean
    fun logout(): Boolean
}