package com.example.yangzhe.learn.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileLiveDataViewModel : ViewModel() {
    val liveDataFirstName: MutableLiveData<String> = MutableLiveData<String>()
    val liveDataLastName: MutableLiveData<String> = MutableLiveData<String>()
    val liveDataAge: MutableLiveData<Integer> = MutableLiveData<Integer>()
}