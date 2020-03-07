package com.example.fireflyhakanfirat.ui.register

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
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: BaseFragment(),View.OnClickListener {

    private lateinit var viewModel: RegisterViewModel

    override fun getLayoutRes() = R.layout.fragment_register

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[RegisterViewModel::class.java]
        observeViewModel()
    }

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)
        showBackButton()
        toolbarTitle = getString(R.string.sign_up)
        registerButton.setOnClickListener(this)
    }

    private fun observeViewModel() {
        viewModel.registerLiveData.observe(
            this,
            Observer { register ->
                when (register.dataState){
                    DataState.LOADING -> {
                        showLoading()
                    }
                    DataState.SUCCESS ->{
                        hideLoading()
                        register.data?.let {
                            Log.i("datastate", it.toString())
                            navigateTo(R.id.action_fragmentRegister_to_fragmentUserList)
                        }
                    }
                    DataState.ERROR -> {
                        hideLoading()
                        register.message?.let {
                            showError(register.message)
                        }
                    }
                }

            })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.registerButton -> {
                v.hideKeyboard()
                if (registerEmailEditText.text.toString().trim().isNotEmpty() ||
                    registerPasswordEditText.text.toString().trim().isNotEmpty()){

                    //viewModel.register(LoginRequest(email = "eve.holt@reqres.in", password = "pistol"))
                    viewModel.register(LoginRequest(email = "eve.holt@reqres.in", password = "pistol"))
                }
                else {
                    DialogUtils.showMessage(context,getString(R.string.user_input_warning))
                }
            }
        }
    }
}