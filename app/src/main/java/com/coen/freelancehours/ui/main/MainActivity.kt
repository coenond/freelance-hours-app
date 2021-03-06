package com.coen.freelancehours.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.coen.freelancehours.R
import com.coen.freelancehours.ui.dashboard.DashboardFragment
import com.coen.freelancehours.ui.hour.HourFragment
import com.coen.freelancehours.ui.project.ProjectFragment
import com.coen.freelancehours.ui.tax.TaxFragment
import com.coen.freelancehours.ui.user.UserFragment

class MainActivity: AppCompatActivity() {

    var content: FrameLayout? = null
    lateinit var title: String

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_hours -> {
                val fragment = HourFragment.newInstance()
                addFragment(fragment)
                setTitle(item.title)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_project -> {
                val fragment = ProjectFragment.newInstance()
                addFragment(fragment)
                setTitle(item.title)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = DashboardFragment.newInstance()
                addFragment(fragment)
                setTitle(item.title)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tax -> {
                val fragment = TaxFragment.newInstance()
                addFragment(fragment)
                setTitle(item.title)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val fragment = UserFragment.newInstance()
                addFragment(fragment)
                setTitle(item.title)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        content = findViewById(R.id.content)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_dashboard

        setTitle("Dashboard")

        val fragment = DashboardFragment.newInstance()
        addFragment(fragment)
    }

    /**
     * add/replace fragment in container [framelayout]
     */
    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.design_bottom_sheet_slide_in,
                        R.anim.design_bottom_sheet_slide_out
                )
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }
}
