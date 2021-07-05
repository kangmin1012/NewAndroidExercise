package org.techtown.kt_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var mainFragment : MainFragment
    lateinit var menuFragment : MenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragment = MainFragment()
        menuFragment = MenuFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, mainFragment).commit()
    }

    public fun onFragmentChanged(index : Int) {
        Log.d("success","getActivity")
        if (index == 0){
            supportFragmentManager.beginTransaction().replace(R.id.container,menuFragment).commit()
        }
        else {
            supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit()
        }
    }
}
