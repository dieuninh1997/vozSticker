package com.ninhttd.vozsticker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener


class MainActivity : AppCompatActivity() {
    val fm: FragmentManager = supportFragmentManager
    val homeFragment: Fragment = HomeFragment()
    val recentFragment: Fragment = RecentFragment()
    val settingFragment: Fragment = SettingFragment()
    var activeFragment: Fragment = recentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fm.beginTransaction().add(R.id.content, settingFragment, "3").hide(settingFragment).commit()
        fm.beginTransaction().add(R.id.content, homeFragment, "2").hide(homeFragment).commit()
        fm.beginTransaction().add(R.id.content, recentFragment, "1").commit()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_home -> {
                fm.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                activeFragment = homeFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recent -> {
                fm.beginTransaction().hide(activeFragment).show(recentFragment).commit()
                activeFragment = recentFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                fm.beginTransaction().hide(activeFragment).show(settingFragment).commit()
                activeFragment = settingFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



}