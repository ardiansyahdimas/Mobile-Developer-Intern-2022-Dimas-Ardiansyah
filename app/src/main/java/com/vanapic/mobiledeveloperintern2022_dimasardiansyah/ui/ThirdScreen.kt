package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.databinding.ActivityThirdScreenBinding
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui.adapter.UserAdapter
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui.viewmodel.ThirdViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.core.widget.NestedScrollView
import androidx.localbroadcastmanager.content.LocalBroadcastManager




class ThirdScreen : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var viewModel: ThirdViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var rvUser : RecyclerView
    private lateinit var nestedSV: NestedScrollView
    private var numberPage = 1
    private var pageSize = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Third Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar = binding.loading
        rvUser = binding.rvUser
        nestedSV = binding.idNestedSV
        viewModel = ViewModelProvider(this).get(ThirdViewModel::class.java)
        getListUser(numberPage, pageSize)
        refresh()
        loadNextPage()
    }

    private fun refresh(){
        val swipeRefresh = binding.swipeRefreshLayout
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            numberPage = 1
            pageSize = 10
            getListUser(numberPage, pageSize)
        }
    }

    private fun getListUser(numberPage: Int, pageSize:Int){
        viewModel.getListUser(numberPage, pageSize)
        val adapter = UserAdapter()
        adapter.onItemClick = { selectedData ->
            val intent = Intent("data")
            intent.putExtra("VALUE", "${selectedData.firstName} ${selectedData.lastName}")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            finish()
        }
        viewModel.listUser.observe(this, {data ->
            if(data.isNotEmpty()){
                progressBar.setVisibility(View.GONE)
                adapter.setData(data)
            } else {
                Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show()
            }
        })
        with(rvUser) {
            this.layoutManager = LinearLayoutManager(this@ThirdScreen)
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadNextPage(){
        nestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                progressBar.setVisibility(View.VISIBLE)
                pageSize++
                getListUser(numberPage,pageSize)
                if (pageSize   > 10  ) {
                    numberPage++
                    pageSize = 10
                    getListUser(numberPage, pageSize)
                }
            }
        })
    }
}