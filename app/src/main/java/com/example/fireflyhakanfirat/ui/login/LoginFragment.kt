package com.example.fireflyhakanfirat.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fireflyhakanfirat.R
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.data.DataState
import com.example.fireflyhakanfirat.extensions.hideKeyboard
import com.example.fireflyhakanfirat.ui.BaseFragment
import com.example.fireflyhakanfirat.utils.DialogUtils
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment(),View.OnClickListener {

    private lateinit var viewModel: LoginViewModel

    override fun getLayoutRes() = R.layout.fragment_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        observeViewModel()
    }

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)

        hideBackButton()
        toolbarTitle = getString(R.string.sign_in)
        loginButton.setOnClickListener(this)
        registerTextView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.loginButton -> {
                v.hideKeyboard()

                if (emailEditText.text.toString().trim().isNotEmpty() ||
                    passwordEditText.text.toString().trim().isNotEmpty()){

                    //viewModel.login(LoginRequest("eve.holt@reqres.in", "cityslicka"))
                    viewModel.login(LoginRequest(emailEditText.text.toString().trim(),
                        passwordEditText.text.toString().trim()))
                }
                else {
                    DialogUtils.showMessage(context,getString(R.string.user_input_warning))
                }
            }
            R.id.registerTextView -> {
                navigateTo(R.id.action_fragmentLogin_to_fragmentRegister)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.loginLiveData.observe(
            this,
            Observer { login ->
                when (login.dataState){
                    DataState.LOADING -> {
                        showLoading()
                    }
                    DataState.SUCCESS ->{
                        hideLoading()
                        login.data?.let {
                            Log.i("datastate", login.data.toString())
                            navigateTo(R.id.action_fragmentLogin_to_fragmentUserList)
                        }
                    }
                    DataState.ERROR -> {
                        hideLoading()
                        login.message?.let {
                            showError(login.message)
                        }
                    }
                }
            })
    }

}