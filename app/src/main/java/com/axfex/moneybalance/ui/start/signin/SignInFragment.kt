package com.axfex.moneybalance.ui.start.signin

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.LinearInterpolator
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.SigninViewState
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.SigninViewState.*
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.TypingViewState
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.TypingViewState.*
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.SignModeViewState
import com.axfex.moneybalance.ui.start.signin.SignInViewModel.SignModeViewState.*
import kotlinx.android.synthetic.main.fragment_signin.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.utils.*
import androidx.transition.*
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject
import android.view.ViewGroup

class SignInFragment : AppFragment() {

    @Inject
    lateinit var viewModel: SignInViewModel

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
        setupListeners()
    }

    fun setupListeners() {
        toSignUp.setOnClickListener { viewModel.switchToSignUp() }
        toSignIn.setOnClickListener { viewModel.switchToSignIn() }
        username.setOnFocusChangeListener(this::focusChanged)
        email.setOnFocusChangeListener(this::focusChanged)
        password.setOnFocusChangeListener(this::focusChanged)
        background.setOnFocusChangeListener(this::focusChanged)

        password.setOnEditorActionListener { _: TextView?, i: Int, _: KeyEvent? ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                signIn.callOnClick()
            }
            false
        }

        skip.setOnClickListener {
            viewModel.signInAnonimously()
            findNavController().navigate(R.id.action_signInFragment_to_readyFragment)
        }


        signIn.setOnClickListener { view ->
            viewModel.finishTyping()
            view.hideKeyboard()
            viewModel.signIn(
                email.text.toString(),
                password.text.toString()
            )
        }

        signUp.setOnClickListener { view ->
            viewModel.finishTyping()
            view.hideKeyboard()
            viewModel.signUp(
                username.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        addKeyboardEventListener { isOpen ->
            if (isOpen) {
                view?.post {
                    viewModel.beginTyping()
                }
            } else {
                view?.post {
                    viewModel.finishTyping()
                }
            }
        }


    }

    fun focusChanged(view: View, hasFocus: Boolean) {
        if ((view is TextInputEditText) && hasFocus) {
            viewModel.beginTyping()
            view.showKeyboard()
        } else {
            view.hideKeyboard()
            viewModel.finishTyping()
        }
    }


//    fun onSignUp(view: View) {
//        findNavController().navigate(R.id.action_fragment_login_to_signUpFragment)
//    }
//


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.signinState.subscribe(this, this::render)
        viewModel.typingState.subscribe(this, this::renderLogo)
        viewModel.signModeState.subscribe(this, this::renderSignView)

    }

    private fun render(state: SigninViewState) {
        val transition = TransitionSet()
            .addTransition(ChangeTransform())
            .addTransition(ChangeClipBounds())
            .addTransition(Fade())
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 250L
        TransitionManager.beginDelayedTransition(signInRoot, transition)

        errorMessage.gone()

        emailLayout.error = null
        emailLayout.isErrorEnabled = false

        passwordLayout.error = null
        passwordLayout.isErrorEnabled = false

        when (state) {

            Default -> {
                username.enable()
                email.enable()
                password.enable()
                forgotPassword.enable()
                signIn.enable()
                signUp.enable()
                toSignIn.enable()
                toSignUp.enable()
                loading.invisible()
                errorMessage.gone()
            }

            Loading -> {
                username.disable()
                email.disable()
                password.disable()
                forgotPassword.disable()
                signIn.disable()
                signUp.disable()
                toSignIn.disable()
                toSignUp.disable()
                loading.visible()
                errorMessage.gone()
            }

            EmailRequired -> {
                val requiredHint = requireContext().getString(R.string.requiredHint)
                emailLayout.error = requiredHint
            }

            PasswordRequired -> {
                val requiredHint = requireContext().getString(R.string.requiredHint)
                passwordLayout.error = requiredHint
            }

            is Error -> {
                username.enable()
                email.enable()
                password.enable()
                forgotPassword.enable()
                signIn.enable()
                signUp.enable()
                toSignIn.enable()
                toSignUp.enable()
                loading.invisible()
                errorMessage.visible()
                errorMessage.text = state.errorMessage
            }

        }

    }

    private fun renderLogo(typingState: TypingViewState) {

        val transition = TransitionSet()
            .addTransition(ChangeTransform())
            .addTransition(ChangeClipBounds())
            .addTransition(Fade())
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 250L
        TransitionManager.beginDelayedTransition(signInRoot, transition)


        when (typingState) {
            Idle -> {
                logoImage.visible()
                logoLabel.visible()
                guidelineHeader.setGuidelinePercent(0.26f)
                guidelineFooter.setGuidelinePercent(0.74f)
            }

            Typing -> {
                logoImage.visible()
                logoLabel.gone()
                guidelineHeader.setGuidelinePercent(0.14f)
                guidelineFooter.setGuidelinePercent(0.64f)
            }

        }

    }

    private fun renderSignView(signModeViewState: SignModeViewState) {

        val transition = TransitionSet()
            .addTransition(ChangeTransform())
            .addTransition(ChangeClipBounds())
            .addTransition(Fade())
            .addTransition(ChangeBounds())
        transition.interpolator = LinearInterpolator()
        transition.duration = 250L
        TransitionManager.beginDelayedTransition(signInRoot, transition)

        when (signModeViewState) {
            SignIn -> {
                usernameLayout.gone()
                forgotPassword.visible()
                signIn.visible()
                signUp.invisible()
                toSignIn.invisible()
                toSignUp.visible()
                loading.invisible()
            }

            SignUp -> {
                usernameLayout.visible()
                forgotPassword.gone()
                signIn.invisible()
                signUp.visible()
                toSignIn.visible()
                toSignUp.invisible()
            }

        }

        TransitionManager.beginDelayedTransition(signInRoot, transition)
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