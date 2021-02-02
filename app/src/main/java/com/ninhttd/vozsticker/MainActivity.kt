package com.ninhttd.vozsticker

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val fm: FragmentManager = supportFragmentManager
    private val homeFragment: Fragment = HomeFragment()
    private val recentFragment: Fragment = RecentFragment()
    private val settingFragment: Fragment = SettingFragment()
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

    private fun loadImages() {
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(ImageService::class.java)
       //https://api.imgur.com/3/album/PyAepyl?client_id=88c1a76b6435585
        val requestCall =destinationService.getAll("PyAepyl", "88c1a76b6435585");
        //make network call asynchronously
        requestCall?.enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val imageList  = response.body()!!
                    Log.d("Response", "imageList size : ${imageList.size}")
//                    country_recycler.apply {
//                        setHasFixedSize(true)
//                        layoutManager = GridLayoutManager(this@MainActivity,2)
//                        adapter = CountriesAdapter(response.body()!!)
//                    }
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })

    }


}