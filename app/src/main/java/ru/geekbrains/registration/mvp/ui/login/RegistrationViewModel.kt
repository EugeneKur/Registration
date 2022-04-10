package ru.geekbrains.registration.mvp.ui.login

import ru.geekbrains.registration.mvp.domain.RegistrationUsecase
import ru.geekbrains.registration.mvp.utils.Signer

class RegistrationViewModel(private val registrationUsecase: RegistrationUsecase) :
    RegistrationContracts.ViewModel {
    override val shouldShowProgress: Signer<Boolean> = Signer()
    override val isSuccess: Signer<Boolean> = Signer()
    override val error: Signer<String> = Signer(true)


    override fun onLogin(login: String, password: String) {

        shouldShowProgress.post(true)

        registrationUsecase.login(login, password) { result ->
            shouldShowProgress.post(false)
            if (checkCredentials(result)) {
                isSuccess.post(true)
                error.post("")
            } else {
                isSuccess.post(false)
                error.post(result)
            }
        }

//        view?.showProgress()
//
//        registrationUsecase.login(login, password) { result ->
//            view?.hideProgress()
//            if (checkCredentials(result)) {
//                view?.setSuccess()
//                isSuccess = true
//                errorText = ""
//            } else {
//                errorText = result
//                view?.setError(errorText)
//                isSuccess = false
//            }
//        }
    }

    private fun checkCredentials(result: String): Boolean {
        return result == "Успех!"
    }

    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}