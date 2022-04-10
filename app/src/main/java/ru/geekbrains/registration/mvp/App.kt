package ru.geekbrains.registration.mvp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import ru.geekbrains.registration.mvp.data.FakeLoginApiImpl
import ru.geekbrains.registration.mvp.data.RegistrationUsecaseImpl
import ru.geekbrains.registration.mvp.domain.LoginApi
import ru.geekbrains.registration.mvp.domain.RegistrationUsecase

class App : Application() {
    private val api: LoginApi by lazy {
        FakeLoginApiImpl()
    }
    val loginUsecase: RegistrationUsecase by lazy {
        RegistrationUsecaseImpl(app.api)
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }