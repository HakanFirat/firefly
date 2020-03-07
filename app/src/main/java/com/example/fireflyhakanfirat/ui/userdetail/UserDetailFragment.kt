package com.example.fireflyhakanfirat.ui.userdetail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fireflyhakanfirat.R
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.data.DataState
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.ui.BaseFragment
import com.example.fireflyhakanfirat.utils.DialogUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment: BaseFragment(), View.OnClickListener,TextWatcher {

    private var userModel: User? = null
    private var userId: Int? = null
    private lateinit var viewModel: UserDetailViewModel

    override fun onArguments(arguments: Bundle) {
        userId = arguments.getInt("ITEM_ID")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UserDetailViewModel::class.java]
        observeViewModel()
        viewModel.getUser(userId!!)
    }

    override fun getLayoutRes() = R.layout.fragment_user_detail

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)

        toolbarTitle = getString(R.string.userDetail)
        setToolbarVisibility(true)
        showBackButton()

        nameEditText.addTextChangedListener(this)
        jobEditText.addTextChangedListener(this)

        updateButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.updateButton -> {
                //Ornek parametreler içeren data
                //viewModel.updateUser(User(id = 7, firstname = "Michael"), UpdateUserRequest("ggg", "pilott"))
                viewModel.updateUser(User(id = userModel!!.id,
                    firstname = userModel?.firstname.toString().trim()),
                    UpdateUserRequest(nameEditText.text.toString().trim(),
                        jobEditText.text.toString().trim()))
            }

            R.id.deleteButton -> {
                //Ornek parametreler içeren data
                //viewModel.deleteUser(User(id = 7, firstname = "Michael"))
                viewModel.deleteUser(User(id = userId!!, firstname = userModel?.firstname))
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (nameEditText.text.toString().trim().isEmpty() &&
                jobEditText.text.toString().trim().isEmpty()){
            updateButton.setBackgroundResource(R.drawable.button_unselected)
            updateButton.isEnabled = false
        }
        else{
            updateButton.setBackgroundResource(R.drawable.button_selected)
            updateButton.isEnabled = true
        }
    }

    private fun observeViewModel() {

        viewModel.getUserLiveData.observe(
            this,
            Observer { user ->
                when (user.dataState){
                    DataState.LOADING -> {
                        showLoading()
                    }
                    DataState.SUCCESS ->{
                        hideLoading()
                        user.data?.let {
                            userModel = it
                            setUserData()
                        }
                    }
                    DataState.ERROR -> {
                        hideLoading()
                        user.message?.let {
                            showError(user.message)
                        }
                    }
                }

            })

        viewModel.updateUserLiveData.observe(
            this,
            Observer { updatedUser ->
                when (updatedUser.dataState){
                    DataState.LOADING -> {
                        showLoading()
                    }
                    DataState.SUCCESS ->{
                        hideLoading()
                        updatedUser.data?.let {
                            DialogUtils.showDialog(context,getString(R.string.update_success),
                                positiveButtonPressed = {
                                    onBackPressed()
                                } )
                            Log.i("datastate", updatedUser.data.toString())
                        }
                    }
                    DataState.ERROR -> {
                        hideLoading()
                        updatedUser.message?.let {
                            showError(updatedUser.message)
                        }
                    }
                }

            })

        viewModel.deleteUserLiveData.observe(
            this,
            Observer { deletedUser ->
                when (deletedUser.dataState){
                    DataState.LOADING -> {
                        showLoading()
                    }
                    DataState.SUCCESS ->{
                        hideLoading()
                        DialogUtils.showDialog(context,getString(R.string.delete_success),
                            positiveButtonPressed = {
                                onBackPressed()
                            } )
                        Log.i("datastate", deletedUser.data.toString())
                    }
                    DataState.ERROR -> {
                        hideLoading()
                        deletedUser.message?.let {
                            showError(deletedUser.message)
                        }
                    }
                }
            })
    }

    private fun setUserData(){
        Picasso.get().load(userModel?.avatar).into(avatarImageView)
        emailEditText.setText(userModel?.email)
        fullNameEditText.setText(userModel?.firstname+" "+ userModel?.lastname)
    }

}