package com.axfex.moneybalance.ui.login

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import com.axfex.moneybalance.databinding.FragmentLoginBinding
import com.axfex.moneybalance.ui.login.LoginViewModel.LoginViewState
import com.axfex.moneybalance.ui.login.LoginViewModel.LoginViewState.*
import com.axfex.moneybalance.ui.login.LoginViewModel.LogoViewState
import com.axfex.moneybalance.ui.login.LoginViewModel.LogoViewState.*
import com.axfex.moneybalance.ui.login.LoginViewModel.SignModeViewState
import com.axfex.moneybalance.ui.login.LoginViewModel.SignModeViewState.*
import kotlinx.android.synthetic.main.fragment_login.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.utils.*
import androidx.transition.*
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var focusedView: View
    val TAG = "LoginFragment"
    var signUpMode = false

    var idleImageHeight = 0
//    lateinit var editingLayoutParams: ConstraintLayout.LayoutParams

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater, container, false)


        binding.toSignUp.setOnClickListener { viewModel.switchToSignUp() }
        binding.toSignIn.setOnClickListener { viewModel.switchToSignIn() }

        binding.username.setOnFocusChangeListener(this::focusChanged)
        binding.email.setOnFocusChangeListener(this::focusChanged)
        binding.password.setOnFocusChangeListener(this::focusChanged)

        binding.password.setOnEditorActionListener { textView: TextView?, i: Int, keyEvent: KeyEvent? ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                signIn.callOnClick()
                true
            }
            false
        }

        binding.emptySpace.setOnFocusChangeListener(this::focusChanged)

        binding.signIn.setOnClickListener {
            viewModel.signUp(binding.username.text.toString(), binding.password.text.toString())
        }

        binding.signUp.setOnClickListener(this::onSignUp)

        return binding.root
    }

    fun onSignUp(view:View){
    findNavController().navigate(R.id.action_fragment_login_to_signUpFragment)
}

    fun focusChanged(view: View, hasFocus: Boolean) {
        if ((view is TextInputEditText ) && hasFocus) {
            viewModel.beginTyping()
            showKeyboard(view)
        } else if (view == empty_space) {
            viewModel.finishTyping()
            hideKeyboard(view)
        }
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

    fun showKeyboard(view: View) {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        viewModel.state.subscribe(this, this::render)
        viewModel.logoState.subscribe(this, this::renderLogo)
        viewModel.signModeState.subscribe(this, this::renderSignView)
    }

    private fun render(state: LoginViewState) {
//

        val transition = TransitionSet()
            .addTransition(ChangeTransform())
            .addTransition(ChangeClipBounds())
            .addTransition(Fade())
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 150L


        when (state) {


            LoginViewLoadingState -> {
                signIn.disable()
                loading.visible()
                email.disable()
                password.disable()
                toSignUp.invisible()
                forgotPassword.invisible()
                errorMessage.invisible()
                errorMessage.text=""
                orText.visible()
            }

            is LoginViewWithErrorState -> {
                signIn.enable()
                loading.invisible()
                email.enable()
                password.enable()
                toSignUp.visible()
                forgotPassword.visible()
                logoImage.layoutParams.height = idleImageHeight
                errorMessage.visible()
                errorMessage.text=state.errorMessage
                orText.invisible()
            }
        }
        TransitionManager.beginDelayedTransition(loginContainer, transition)
    }

    private fun renderLogo(logoState: LogoViewState) {

        val transition = TransitionSet()
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 150L


        when (logoState) {
            LogoBigView -> {
                logoImage.visible()
                logoLabel.visible()
                guidelineHeader.setGuidelinePercent(0.28f)
                (usernameLayout.layoutParams as ConstraintLayout.LayoutParams).verticalBias=0.5f
            }

            LogoSmallView -> {

                logoImage.visible()
                logoLabel.visible()
                guidelineHeader.setGuidelinePercent(0.16f)
                (usernameLayout.layoutParams as ConstraintLayout.LayoutParams).verticalBias=0.0f
                logoLabel.gone()
            }
            NoLogoView ->{
                logoImage.gone()
                logoLabel.gone()
                guidelineHeader.setGuidelinePercent(0.0f)
            }


        }

        TransitionManager.beginDelayedTransition(loginContainer, transition)
    }

    private fun renderSignView(signModeViewState: SignModeViewState) {

        val transition = TransitionSet()
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 150L


        when (signModeViewState) {
            SignInView -> {
                email.requestFocus()
                forgotPassword.visible()
                signIn.visible()
                signUp.invisible()
                toSignIn.invisible()
                toSignUp.visible()
                usernameLayout.gone()

            }

            SignUpView -> {
                username.requestFocus()
                forgotPassword.invisible()
                signUp.visible()
                signIn.invisible()
                toSignIn.visible()
                toSignUp.invisible()
                usernameLayout.visible()

            }

        }

        TransitionManager.beginDelayedTransition(loginContainer, transition)
    }


}
