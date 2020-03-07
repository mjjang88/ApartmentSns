package com.mjjang.apartmentsns.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.apartmentsns.data.Post
import com.mjjang.apartmentsns.databinding.ListItemPostBinding

class PostListAdapter : ListAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItem(position)
        (holder as PostViewHolder).bind(post)
    }

    class PostViewHolder(
        private val binding : ListItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.post?.let { TODO("항목 클릭 했을 때 동작을 추가") }
            }
        }

        fun bind(item: Post) {
            binding.apply {
                post = item
                executePendingBindings()
            }
        }
    }
}

private class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.post_id == newItem.post_id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}