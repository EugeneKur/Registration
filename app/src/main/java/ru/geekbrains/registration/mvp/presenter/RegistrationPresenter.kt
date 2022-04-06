package ru.geekbrains.registration.mvp.presenter


import ru.geekbrains.registration.mvp.FakeLoginApiImpl
import ru.geekbrains.registration.mvp.RegistrationContracts
import ru.geekbrains.registration.mvp.model.RegistrationModel
import java.lang.Thread.sleep


class RegistrationPresenter: RegistrationContracts.Presenter {

    private var view: RegistrationContracts.View? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""
    private var registrationModel: FakeLoginApiImpl? = null

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
                    errorText = registrationModel?.login(login, password) ?: ""
                    view?.setError(errorText)
                    isSuccess = false
                }
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        registrationModel = FakeLoginApiImpl()
        return registrationModel?.login(login, password) == "Успех!"
    }

    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}