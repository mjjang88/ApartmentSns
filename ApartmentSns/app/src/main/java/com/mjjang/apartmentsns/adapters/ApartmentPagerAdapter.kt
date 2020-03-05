package com.mjjang.apartmentsns.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val MY_FAVORITE_PAGE_INDEX = 0
const val APARTMENT_LIST_PAGE_INDEX = 1

class ApartmentPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_FAVORITE_PAGE_INDEX to { MyFavoriteFragment() },
        APARTMENT_LIST_PAGE_INDEX to { ApartmentListFragment() }
    )

    override fun getItemCount(): Int {
        tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}