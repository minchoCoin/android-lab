package com.example.week7_ex5

import MainFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MyPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val fragments : List<Fragment> = listOf(MainFragment(),SubFragment())
    override fun getItemCount(): Int = 1000

    override fun createFragment(position: Int): Fragment {

        if (position %2 ==  0) {
            return MainFragment()
        }
        else  {
            return SubFragment()
        }
    }


//    override fun getItemCount(): Int {
//        // 페이지의 개수를 반환
//        return 2
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        // 각 페이지에 표시될 Fragment를 반환
//        return when (position) {
//            0 -> MainFragment()
//            1 -> SubFragment()
//            else -> throw IllegalArgumentException("Invalid position: $position")
//        }
//    }
}
