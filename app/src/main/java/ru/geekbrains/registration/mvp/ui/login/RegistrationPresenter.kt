package ru.geekbrains.registration.mvp.ui.login


import ru.geekbrains.registration.mvp.data.FakeLoginApiImpl
import ru.geekbrains.registration.mvp.domain.LoginApi
import java.lang.Thread.sleep


class RegistrationPresenter: RegistrationContracts.Presenter {

    private var view: RegistrationContracts.View? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""
    private val api: LoginApi = FakeLoginApiImpl()

    override fun onAttach(view: RegistrationContracts.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError(errorText)
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(1000)
            view?.getHandler()?.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                } else {
                    errorText = api.login(login, password)
                    view?.setError(errorText)
                    isSuccess = false
                }
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return api.login(login, password) == "Успех!"
    }

    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}