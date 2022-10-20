package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.network.ApiConfig
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.response.ListUserResponse
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<UserResponse>>()
    val listUser: LiveData<List<UserResponse>> = _listUser

    fun getListUser(numberPage: Int, pageSize: Int){
        val client = ApiConfig.instance.getListUser(numberPage, pageSize)
        client.enqueue(object : Callback<ListUserResponse> {
                override fun onResponse(
                    call: Call<ListUserResponse>,
                    response: Response<ListUserResponse>
                ) {
                    if (response.code() == 200) {
                        val data = response.body()?.data
                        _listUser.postValue(data)
                    }
                }
                override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {

                }
            })
    }
}