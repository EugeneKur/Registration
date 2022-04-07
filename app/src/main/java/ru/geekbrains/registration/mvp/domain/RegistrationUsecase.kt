package ru.geekbrains.registration.mvp.domain

interface RegistrationUsecase {
    fun login(login: String, password: String, callback: (String) -> Unit)
}