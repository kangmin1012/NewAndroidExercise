package org.sample.motionlayoutsample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm,
BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private var fragments : ArrayList<SampleFragment> = arrayListOf()

    override fun getItem(position: Int): Fragment  = fragments[position]

    override fun getCount(): Int = fragments.size

    fun addItems(fragment : SampleFragment){
        fragments.add(fragment)
    }
}