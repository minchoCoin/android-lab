package com.example.week7_ex5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.week7_ex5.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewpager.adapter = MyPagerAdapter(this)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val drawer = findViewById<DrawerLayout>(R.id.drawer)
        toggle = ActionBarDrawerToggle(this,drawer,R.string.drawer_opened
            ,R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){return true}
        return super.onOptionsItemSelected(item)
    }
}


//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    lateinit var toggle: ActionBarDrawerToggle
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val viewPager: ViewPager2 = binding.viewPager
//        val pagerAdapter = MyPagerAdapter(this)
//        viewPager.adapter = pagerAdapter
//        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//
//        val fragButton = findViewById<Button>(R.id.fragBut1)
//        val fragmentManager: FragmentManager = supportFragmentManager
//        var onClicked = false
//        fragButton.setOnClickListener {
//            if (onClicked) {
//                onClicked = false
//                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//                val frameLayout = supportFragmentManager.findFragmentById(R.id.fragment_content)
//                transaction.remove(frameLayout!!).commit()
//            } else {
//                onClicked = true
//                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//                transaction.add(R.id.fragment_content, Onefragment()).commit()
//            }
//        }
//
//        toggle = ActionBarDrawerToggle(
//            this, binding.drawer,
//            R.string.drawer_opened, R.string.drawer_closed
//        )
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toggle.syncState()
//    }
//}


