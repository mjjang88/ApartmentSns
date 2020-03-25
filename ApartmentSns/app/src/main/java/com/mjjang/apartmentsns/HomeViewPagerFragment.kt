package com.mjjang.apartmentsns

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mjjang.apartmentsns.adapters.APARTMENT_LIST_PAGE_INDEX
import com.mjjang.apartmentsns.adapters.ApartmentPagerAdapter
import com.mjjang.apartmentsns.data.ApartmentRepository
import com.mjjang.apartmentsns.databinding.FragmentViewPagerBinding
import com.mjjang.apartmentsns.manager.AppPreference
import com.mjjang.apartmentsns.manager.KeyboardManager
import com.mjjang.apartmentsns.viewmodels.ApartmentListViewModel

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

        binding.btnSearch.setOnClickListener {
            binding.layoutTitle.visibility = View.GONE
            binding.layoutSearch.visibility = View.VISIBLE

            binding.editSearch.requestFocus()
            KeyboardManager.showKeyboard(context, binding.editSearch)
        }

        binding.btnClear.setOnClickListener {
            binding.layoutSearch.visibility = View.GONE
            binding.layoutTitle.visibility = View.VISIBLE

            KeyboardManager.hideKeyboard(context, binding.editSearch)
            binding.editSearch.text = null
        }

        binding.editSearch.addTextChangedListener { text: Editable? ->
            mSearchEditTextChangedListener?.OnTextChanged(text.toString())
        }

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

    interface searchEditTextChangedListener {
        fun OnTextChanged(searchWord: String?)
    }

    private var mSearchEditTextChangedListener: searchEditTextChangedListener? = null
    fun setSearchEditTextChangedListener(listener: (String?) -> Unit) {
        mSearchEditTextChangedListener = object : searchEditTextChangedListener {
            override fun OnTextChanged(searchWord: String?) {
                listener(searchWord)
            }
        }
    }
}