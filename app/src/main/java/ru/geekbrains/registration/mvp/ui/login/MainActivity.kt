package ru.geekbrains.registration.mvp.ui.login

import android.app.Activity
import android.content.Intent
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
import ru.geekbrains.registration.databinding.ActivityMainBinding
import ru.geekbrains.registration.mvp.app
import ru.geekbrains.registration.mvp.ui.registration.RegistrationActivity
import ru.geekbrains.registration.mvp.ui.restore.RestoreActivity


class MainActivity : AppCompatActivity(), RegistrationContracts.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: RegistrationContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = initPresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginAuthenticationEditText.text.toString(),
                binding.passwordAuthenticationEditText.text.toString()
            )
        }
        binding.registrationButton.setOnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        }
        binding.forgotPasswordButton.setOnClickListener {
            startActivity(Intent(applicationContext, RestoreActivity::class.java))
        }
    }

    private fun initPresenter(): RegistrationPresenter {
        val presenter = lastCustomNonConfigurationInstance as? RegistrationPresenter
        return presenter ?: RegistrationPresenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.registrationButton.isVisible = false
        binding.forgotPasswordButton.isVisible = false
        binding.root.setBackgroundColor(Color.BLUE)
        binding.resultAuthenticationTextView.text = "Вы вошли!"
        Toast.makeText(this, "Успех!", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun setError(error: String) {
        binding.resultAuthenticationTextView.text = "не верный логин или пароль"
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
        binding.loadingAuthenticationTextView.isVisible = true
        binding.progressAuthenticationProgressBar.isVisible = true
    }

    @MainThread
    override fun hideProgress() {
        binding.loginButton.isEnabled = true
        binding.loadingAuthenticationTextView.isVisible = false
        binding.progressAuthenticationProgressBar.isVisible = false
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