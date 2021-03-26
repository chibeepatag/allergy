package com.chibee.allergy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chibee.allergy.data.Allergy
import com.chibee.allergy.databinding.AllergyCardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllergyCardRecycleViewAdapter(val clickListener: AllergyListener) : ListAdapter<Allergy,
        RecyclerView.ViewHolder>(AllergyDiffCallback()){

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun launchSubmitList(list: List<Allergy>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val allergy = getItem(position)
        holder.bind(clickListener, allergy)
    }

    class ViewHolder private constructor(val binding: AllergyCardBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: AllergyListener, item: Allergy) {
            binding.allergy = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AllergyCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class AllergyDiffCallback : DiffUtil.ItemCallback<Allergy>() {
    override fun areItemsTheSame(oldItem: Allergy, newItem: Allergy): Boolean {
        return oldItem.allergyId == newItem.allergyId
    }

    override fun areContentsTheSame(oldItem: Allergy, newItem: Allergy): Boolean {
        return oldItem == newItem
    }
}

class AllergyListener(val clickListener: (allergyId: Long) -> Unit) {
    fun onClick(allergy: Allergy) = clickListener(allergy.allergyId)
}

