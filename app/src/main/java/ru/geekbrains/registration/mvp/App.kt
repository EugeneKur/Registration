package ru.geekbrains.registration.mvp

import android.app.Application
import android.content.Context
import ru.geekbrains.registration.mvp.data.FakeLoginApiImpl
import ru.geekbrains.registration.mvp.domain.LoginApi

class App: Application() {
    val api: LoginApi by lazy {
        FakeLoginApiImpl()
    }
}

val Context.app: App
get() {
    return applicationContext as App
}