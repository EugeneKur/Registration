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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.geekbrains.registration.databinding.ActivityMainBinding
import ru.geekbrains.registration.mvp.app
import ru.geekbrains.registration.mvp.ui.registration.RegistrationActivity
import ru.geekbrains.registration.mvp.ui.restore.RestoreActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewModel: RegistrationContracts.ViewModel? = null
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = initViewModel()

        binding.loginButton.setOnClickListener {
            viewModel?.onLogin(
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

        viewModel?.shouldShowProgress?.subscribe(handler) { shouldShow ->
            if (shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel?.isSuccess?.subscribe(handler) {
            if (it == true) {
                setSuccess()
            }
        }

        viewModel?.error?.subscribe(handler) {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if (success == false) {
                    setError(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.shouldShowProgress?.unsubscribeAll()
        viewModel?.isSuccess?.unsubscribeAll()
        viewModel?.error?.unsubscribeAll()
    }

    private fun initViewModel(): RegistrationViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? RegistrationViewModel
        return viewModel ?: RegistrationViewModel(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.registrationButton.isVisible = false
        binding.forgotPasswordButton.isVisible = false
        binding.root.setBackgroundColor(Color.BLUE)
        binding.resultAuthenticationTextView.text = "Вы вошли!"
        Toast.makeText(this, "Успех!", Toast.LENGTH_SHORT).show()
    }

    private fun setError(error: String) {
        binding.resultAuthenticationTextView.text = "не верный логин или пароль"
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
        binding.loadingAuthenticationTextView.isVisible = true
        binding.progressAuthenticationProgressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.loginButton.isEnabled = true
        binding.loadingAuthenticationTextView.isVisible = false
        binding.progressAuthenticationProgressBar.isVisible = false
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