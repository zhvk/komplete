package com.zhvk.komplete.category_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.komplete.database.CategoryOfTasks
import com.zhvk.komplete.databinding.ItemCategoryBinding

class CategoryListAdapter(
    private val clickListener: (CategoryOfTasks) -> Unit
) : ListAdapter<CategoryOfTasks, CategoryListAdapter.CategoryOfTasksViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<CategoryOfTasks>() {
        override fun areItemsTheSame(oldItem: CategoryOfTasks, newItem: CategoryOfTasks): Boolean {
            return oldItem.category.categoryId == newItem.category.categoryId
        }

        override fun areContentsTheSame(oldItem: CategoryOfTasks, newItem: CategoryOfTasks): Boolean {
            return oldItem.category.title == newItem.category.title // TODO: compare (done) tasks
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryOfTasksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryOfTasksViewHolder(
            ItemCategoryBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryOfTasksViewHolder, position: Int) {
        val categoryOfTasks = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(categoryOfTasks)
        }
        holder.bind(categoryOfTasks)
    }

    class CategoryOfTasksViewHolder(
        private var binding: ItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryOfTasks: CategoryOfTasks) {
            binding.categoryOfTasks = categoryOfTasks
            binding.executePendingBindings()
        }
    }
}