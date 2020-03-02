package org.techtown.livedata.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel(){

    val currentName : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}