package com.example.fireflyhakanfirat.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fireflyhakanfirat.R
import com.example.fireflyhakanfirat.data.DataState
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlin.collections.ArrayList

class UserListFragment : BaseFragment() {

    private var userList: ArrayList<User> = arrayListOf()
    private var userListAdapter: UserListAdapter? = null
    private lateinit var viewModel: UserListViewModel

    override fun getLayoutRes() = R.layout.fragment_user_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbarTitle = getString(R.string.userList)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UserListViewModel::class.java]
        observeViewModel()
        viewModel.getUserList(2)
    }

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)

        hideBackButton()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        userListAdapter = UserListAdapter(userList)
        userListAdapter?.setItemClickListener(object : UserListAdapter.ItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                navigateTo(R.id.action_fragmentUserList_to_fragmentUserDetail, Bundle().apply {
                    putInt("ITEM_ID", userList[position].id)
                })
            }
        })
        recyclerView.adapter = userListAdapter
    }

    private fun observeViewModel() {
        viewModel.userListLiveData.observe(
            this,
            Observer { users ->

                when (users.dataState){
                    DataState.LOADING -> {
                        showLoading()
                    }
                    DataState.SUCCESS ->{
                        hideLoading()
                        users.data?.let {
                            userList.clear()
                            userList.addAll(it)
                            userListAdapter?.notifyDataSetChanged()
                        }
                    }
                    DataState.ERROR -> {
                        hideLoading()
                        users.message?.let {
                            showError(users.message)
                        }
                    }
                }
            })
    }
}