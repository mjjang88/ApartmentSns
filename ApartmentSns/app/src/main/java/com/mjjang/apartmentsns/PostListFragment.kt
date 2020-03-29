package com.mjjang.apartmentsns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.mjjang.apartmentsns.adapters.PostListAdapter
import com.mjjang.apartmentsns.databinding.FragmentPostListBinding
import com.mjjang.apartmentsns.utilities.InjectorUtils
import com.mjjang.apartmentsns.viewmodels.PostListViewModel

class PostListFragment : Fragment() {

    private val args: PostListFragmentArgs by navArgs()

    private val postListViewModel: PostListViewModel by viewModels {
        InjectorUtils.providePostListViewModelFactory(this, args.apartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPostListBinding>(
            inflater, R.layout.fragment_post_list, container, false
        ).apply {
            postViewModel = postListViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val adapter = PostListAdapter()
        binding.postList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)

        binding.floatingBtnAddPost.setOnClickListener {  }
        return binding.root
    }

    private fun subscribeUi(adapter: PostListAdapter) {
        postListViewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }
    }
}