package org.techtown.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.livedata.ViewModel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var model : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)

//        ViewModelProviders is deprecated x_x
        model = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        model.currentName.value = "hello"

        val nameObserver = Observer<String>{ newName ->
            Tv_live.text = newName
        }

        model.currentName.observe(this, nameObserver)
    }
}
