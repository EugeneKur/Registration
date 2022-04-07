package ru.geekbrains.registration.mvp.ui.login

import ru.geekbrains.registration.mvp.domain.RegistrationUsecase

class RegistrationPresenter(private val registrationUsecase: RegistrationUsecase) :
    RegistrationContracts.Presenter {

    private var view: RegistrationContracts.View? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""


    override fun onAttach(view: RegistrationContracts.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()

        registrationUsecase.login(login, password) { result ->
            view?.hideProgress()
            if (checkCredentials(result)) {
                view?.setSuccess()
                isSuccess = true
                errorText = ""
            } else {
                errorText = result
                view?.setError(errorText)
                isSuccess = false
            }
        }
    }

    private fun checkCredentials(result: String): Boolean {
        return result == "Успех!"
    }

    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}