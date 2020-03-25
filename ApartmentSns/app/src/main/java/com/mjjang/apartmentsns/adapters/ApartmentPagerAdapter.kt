package com.mjjang.apartmentsns.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mjjang.apartmentsns.ApartmentListFragment

const val APARTMENT_LIST_PAGE_INDEX = 0

class ApartmentPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        APARTMENT_LIST_PAGE_INDEX to { ApartmentListFragment(fragment) }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}