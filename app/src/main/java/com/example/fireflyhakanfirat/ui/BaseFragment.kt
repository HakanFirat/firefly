package com.example.fireflyhakanfirat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.fireflyhakanfirat.api.ApiError
import com.example.fireflyhakanfirat.di.Injectable
import com.example.fireflyhakanfirat.extensions.hideKeyboard
import com.example.fireflyhakanfirat.extensions.isValidJSON
import com.example.fireflyhakanfirat.utils.DialogUtils
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val baseActivity get() = activity as BaseActivity
    private var navigateBoolean: Boolean = true

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutRes(), container, false)
        view?.hideKeyboard()
        setToolbarVisibility(true)
        return view
    }

    open fun onArguments(arguments: Bundle) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let(::onArguments)
        initViewsOnViewCreated(view)
    }

    open fun initViewsOnViewCreated(view: View) {}

    fun navigateTo(navigateTo: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
        if (!navigateBoolean)
            return

        navigateBoolean = false
        Timer().schedule(object : TimerTask() {
            override fun run() {
                navigateBoolean = true
            }
        }, 500)

        findNavController().navigate(navigateTo, bundle, navOptions)
    }

    var toolbarTitle: String = ""
        get() = baseActivity.toolbarTitle
        set(value) {
            baseActivity.toolbarTitle = value
            field = value
        }

    fun hideToolbar() {
        baseActivity.toolbarVisibility = View.GONE
    }

    fun showToolbar() {
        baseActivity.toolbarVisibility = View.VISIBLE
    }

    fun setToolbarVisibility(isVisible: Boolean) {
        baseActivity.toolbarVisibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun hideBackButton(){
        baseActivity.backButtonVisibility = View.GONE
    }

    fun showBackButton(){
        baseActivity.backButtonVisibility = View.VISIBLE
    }

    fun showLoading(){
        baseActivity.showLoading()
    }

    fun hideLoading(){
        baseActivity.hideLoading()
    }

    fun onBackPressed(){
        baseActivity.onBackPressed()
    }

    fun showError(message: String){
        if(message.isValidJSON()){
            val apiError = Gson().fromJson(message, ApiError::class.java)
            DialogUtils.showDialog(context,apiError.error,positiveButtonPressed = {})
        }
        else{
            DialogUtils.showMessage(context,message)
        }
    }
}