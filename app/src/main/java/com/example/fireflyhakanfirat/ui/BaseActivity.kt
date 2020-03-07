package com.example.fireflyhakanfirat.ui

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.fireflyhakanfirat.R
import com.example.fireflyhakanfirat.extensions.hideKeyboard
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    var progressDialog: ProgressDialog? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initToolbar()
        changeStatusBarColor()
    }

    private fun changeStatusBarColor(){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.black, null)
        window.navigationBarColor = ResourcesCompat.getColor(resources,R.color.black, null)
        window.setBackgroundDrawableResource(R.color.white)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private fun initToolbar() {
        if (mToolbar != null)
            setSupportActionBar(mToolbar)

        iv_page_back.setOnClickListener {
            onBackPressed()
        }
    }

    var backButtonVisibility: Int
        get() = iv_page_back?.visibility ?: View.GONE
        set(value) {
            iv_page_back?.visibility = value
        }

    var toolbarTitle: String = ""
        get() = tv_app_title?.text.toString()
        set(value) {
            tv_app_title?.text = value
            field = value
        }

    var toolbarVisibility: Int = View.VISIBLE
        get() = mToolbar?.visibility ?: View.GONE
        set(value) {
            mToolbar?.visibility = value
            field = value
        }

    override fun onBackPressed() {
        window.decorView.hideKeyboard()
        super.onBackPressed()
    }

    fun showLoading(){
        if (progressDialog != null){
            return
        }
        progressDialog = ProgressDialog(this)
        if (!(this as Activity).isFinishing) {
            progressDialog?.show()
        }

        if (progressDialog?.window != null){
            progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        progressDialog?.setContentView(R.layout.progress_dialog)
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
    }

    fun hideLoading(){
        progressDialog?.let {
            if (progressDialog!!.isShowing){
                progressDialog!!.dismiss()
                progressDialog = null
            }
        }
    }
}
