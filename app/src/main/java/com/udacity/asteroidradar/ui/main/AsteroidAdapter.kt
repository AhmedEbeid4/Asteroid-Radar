package com.udacity.asteroidradar.ui.main
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.udacity.asteroidradar.databinding.ItemAsteroidBinding
import com.udacity.asteroidradar.models.Asteroid


class AsteroidAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidPropertyViewHolder>(DiffCallback) {

    class AsteroidPropertyViewHolder(private var binding: ItemAsteroidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid) {
            binding.data = asteroid
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid , newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid , newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AsteroidPropertyViewHolder {
        return AsteroidPropertyViewHolder(
            ItemAsteroidBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AsteroidPropertyViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
        holder.bind(asteroid)
    }

    class OnClickListener(val clickListener: (index: Int) -> Unit) {
        fun onClick(index: Int) = clickListener(index)
    }
}
