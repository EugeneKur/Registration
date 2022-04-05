package ru.geekbrains.registration.mvp.view

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.geekbrains.registration.R
import ru.geekbrains.registration.databinding.ActivityMainBinding
import ru.geekbrains.registration.mvp.RegistrationContracts
import ru.geekbrains.registration.mvp.presenter.RegistrationPresenter


class MainActivity : AppCompatActivity(), RegistrationContracts.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: RegistrationContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = initPresenter()
        presenter?.onAttach(this)

        binding.buttonLogin.setOnClickListener {
            presenter?.onLogin(binding.loginAuthenticationEditText.text.toString(), binding.passwordAuthenticationEditText.text.toString())
        }
    }

    private fun initPresenter(): RegistrationPresenter {
        val presenter = lastCustomNonConfigurationInstance as? RegistrationPresenter
        return presenter ?: RegistrationPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        binding.buttonLogin.isVisible = false
        binding.buttonRegistration.isVisible = false
        binding.buttonForgotPassword.isVisible = false
        binding.root.setBackgroundColor(Color.BLUE)
        binding.resultAuthentication.text = "Вы вошли!"
        Toast.makeText(this, "Успех!", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun setError(error: String) {
        binding.resultAuthentication.text = "не верный логин или пароль"
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        binding.buttonLogin.isEnabled = false
        hideKeyboard(this)
        binding.loadingTextAuthentication.isVisible = true
        binding.progressAuthentication.isVisible = true
    }

    @MainThread
    override fun hideProgress() {
        binding.buttonLogin.isEnabled = true
        binding.loadingTextAuthentication.isVisible = false
        binding.progressAuthentication.isVisible = false
    }

    override fun showLogin() {
        TODO("Not yet implemented")
    }

    override fun showPassword() {
        TODO("Not yet implemented")
    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}