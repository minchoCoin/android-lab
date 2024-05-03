package com.example.android7week_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.android7week_viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for(i in 1..3){datas.add("Item $i")}

        binding.viewpager.adapter = MyPagerAdapter(datas)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val phoneStateListener = object : PhoneStateListener() {
            override fun onServiceStateChanged(serviceState: ServiceState?) {
                super.onServiceStateChanged(serviceState)
                //... ...
            }
        }
    }
}

