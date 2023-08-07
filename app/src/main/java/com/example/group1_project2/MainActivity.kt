package com.example.group1_project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), TopFragment.ButtoClickedListener {

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is TopFragment) {
            fragment.setOnButtoClickedListener(this)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.top_fragment_container, TopFragment())
                .add(R.id.bottom_fragment_container, BottomFragment())
                .commit()
        }
    }

    override fun setStatus(status: Boolean) {
        val callingFragment = supportFragmentManager.findFragmentById(R.id.bottom_fragment_container) as BottomFragment
        //calling the updateText method of the FragmentB
        callingFragment.updateStatus(status)
    }
}