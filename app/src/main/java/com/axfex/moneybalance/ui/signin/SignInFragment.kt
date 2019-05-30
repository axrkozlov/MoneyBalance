package com.axfex.moneybalance.ui.signin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.LinearInterpolator
import com.axfex.moneybalance.ui.signin.SignInViewModel.LoginViewState
import com.axfex.moneybalance.ui.signin.SignInViewModel.LoginViewState.*
import com.axfex.moneybalance.ui.signin.SignInViewModel.LogoViewState
import com.axfex.moneybalance.ui.signin.SignInViewModel.LogoViewState.*
import com.axfex.moneybalance.ui.signin.SignInViewModel.SignModeViewState
import com.axfex.moneybalance.ui.signin.SignInViewModel.SignModeViewState.*
import kotlinx.android.synthetic.main.fragment_signin.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.axfex.moneybalance.utils.*
import androidx.transition.*
import com.axfex.moneybalance.R
import com.google.android.material.textfield.TextInputEditText

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var focusedView: View
    val TAG = "SignInFragment"
    var signUpMode = false

    var idleImageHeight = 0
//    lateinit var editingLayoutParams: ConstraintLayout.LayoutParams

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val binding = FragmentSigninBinding.inflate(inflater, container, false)
//        return binding.root
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toSignUp.setOnClickListener { viewModel.switchToSignUp() }
        toSignIn.setOnClickListener { viewModel.switchToSignIn() }
        username.setOnFocusChangeListener(this::focusChanged)
        email.setOnFocusChangeListener(this::focusChanged)
        password.setOnFocusChangeListener(this::focusChanged)

        password.setOnEditorActionListener { textView: TextView?, i: Int, keyEvent: KeyEvent? ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                signIn.callOnClick()
                true
            }
            false
        }

        emptySpace.setOnFocusChangeListener(this::focusChanged)

        signIn.setOnClickListener {
            viewModel.finishTyping()
            viewModel.signIn(
                email.text.toString(),
                password.text.toString()
            )
        }

        signUp.setOnClickListener {
            viewModel.finishTyping()
            viewModel.signUp(
                username.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        addKeybaordEventListener{isOpen ->
            if (isOpen) {
                view.post {
                    viewModel.beginTyping()
                }
            }  else {
                view.post{
                    viewModel.finishTyping()
                }
            }
        }
    }

    fun focusChanged(view: View, hasFocus: Boolean) {
        if ((view is TextInputEditText) && hasFocus) {
            Log.i("SignInFragment", "focusChanged: ")
            viewModel.beginTyping()
            showKeyboard(view)
        } else if (view == emptySpace) {
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

//    fun onSignUp(view: View) {
//        findNavController().navigate(R.id.action_fragment_login_to_signUpFragment)
//    }
//



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        viewModel.state.subscribe(this, this::render)
        viewModel.logoState.subscribe(this, this::renderLogo)
        viewModel.signModeState.subscribe(this, this::renderSignView)

    }

    private fun render(state: LoginViewState) {
        val transition = TransitionSet()
            .addTransition(ChangeTransform())
            .addTransition(ChangeClipBounds())
            .addTransition(Fade())
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 250L
        TransitionManager.beginDelayedTransition(loginContainer, transition)
        errorMessage.gone()

        emailLayout.error=null
        emailLayout.isErrorEnabled= false

        passwordLayout.error=null
        passwordLayout.isErrorEnabled=false

        when (state) {

            LoginViewIdle->{
                signIn.enable()
                signUp.enable()
                loading.invisible()
                username.enable()
                email.enable()
                password.enable()
                forgotPassword.visible()
            }

            LoginViewLoadingState -> {
                signIn.disable()
                signUp.disable()
                loading.visible()
                username.disable()
                email.disable()
                password.disable()
                forgotPassword.disable()
            }

            LoginViewEmailRequiredState ->{
                val requiredHint=requireContext().getString(R.string.requiredHint)
//                email.requestFocus()
                emailLayout.error=requiredHint
            }

            LoginViewPasswordRequiredState ->{
                val requiredHint=requireContext().getString(R.string.requiredHint)
//                password.requestFocus()
                passwordLayout.error=requiredHint
            }

            is LoginViewWithErrorState -> {
                signIn.enable()
                signUp.enable()
                loading.invisible()
                username.enable()
                email.enable()
                password.enable()
                forgotPassword.enable()
                logoImage.layoutParams.height = idleImageHeight
                errorMessage.visible()
                errorMessage.text = state.errorMessage
            }

        }

    }

    private fun renderLogo(logoState: LogoViewState) {

        val transition = TransitionSet()
            .addTransition(ChangeTransform())
            .addTransition(ChangeClipBounds())
            .addTransition(Fade())
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 250L
        TransitionManager.beginDelayedTransition(loginContainer, transition)


        when (logoState) {
            LogoBigView -> {

//                (usernameLayout.layoutParams as ConstraintLayout.LayoutParams).verticalBias = 0.5f
                logoImage.visible()
                logoLabel.visible()
                guidelineHeader.setGuidelinePercent(0.26f)
                guidelineFooter.setGuidelinePercent(0.72f)
                emptySpace.requestFocus()
            }

            LogoSmallView -> {
//                (usernameLayout.layoutParams as ConstraintLayout.LayoutParams).verticalBias =0.0f
                logoImage.visible()
                logoLabel.gone()
                guidelineHeader.setGuidelinePercent(0.14f)
                guidelineFooter.setGuidelinePercent(0.60f)
            }

        }

    }

    private fun renderSignView(signModeViewState: SignModeViewState) {

        val transition = TransitionSet()
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 250L


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
                forgotPassword.gone()
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


//                val va= ValueAnimator.ofFloat(0.5f,0.0f)
//                    va.addUpdateListener { animation ->
//                (usernameLayout.layoutParams as ConstraintLayout.LayoutParams).verticalBias = animation.animatedValue as Float
//                }
//
//
//                val va2= ValueAnimator.ofFloat(0.28f,0.14f)
//                va2.addUpdateListener { animation ->
//                    guidelineHeader.setGuidelinePercent(animation.animatedValue as Float)
//                }
//
//                val set=AnimatorSet()
//                set.play(va2)
//                    .with(va)
//                set.duration=100
//                set.start()