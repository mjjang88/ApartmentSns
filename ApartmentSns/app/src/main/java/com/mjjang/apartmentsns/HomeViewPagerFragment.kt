package com.mjjang.apartmentsns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mjjang.apartmentsns.adapters.APARTMENT_LIST_PAGE_INDEX
import com.mjjang.apartmentsns.adapters.ApartmentPagerAdapter
import com.mjjang.apartmentsns.adapters.MY_FAVORITE_PAGE_INDEX
import com.mjjang.apartmentsns.databinding.FragmentViewPagerBinding

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveinstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = ApartmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            APARTMENT_LIST_PAGE_INDEX -> R.drawable.tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            APARTMENT_LIST_PAGE_INDEX -> getString(R.string.apartment_list_title)
            else -> null
        }
    }
}