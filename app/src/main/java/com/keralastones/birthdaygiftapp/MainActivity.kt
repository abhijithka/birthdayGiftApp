package com.keralastones.birthdaygiftapp

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.mainContainer

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        navigateToFragment(HomeFragment.newInstance())
    }

    fun navigateToFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(mainContainer.id, fragment).commit()
    }
}
