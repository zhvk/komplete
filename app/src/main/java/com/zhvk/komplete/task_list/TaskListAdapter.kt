package com.zhvk.komplete.task_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.komplete.database.Task
import com.zhvk.komplete.databinding.ItemTaskBinding

private const val TAG = "TaskListAdapter"

class TaskListAdapter(
    private val taskListViewModel: TaskListViewModel
) : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.parentCategoryId == newItem.parentCategoryId &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.completed == newItem.completed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        return TaskViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        val task = getItem(position)
//        holder.itemView.setOnClickListener{
//            clickListener(categoryOfTasks)
//        }
        holder.bind(task, taskListViewModel)
    }

    class TaskViewHolder(
        private var binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task, viewModel: TaskListViewModel) {
            Log.d(TAG, "bind()")
            binding.task = task
            /*binding.taskCompletedCheckbox.setOnCheckedChangeListener { compoundButton, b ->
                viewModel.completeTask(task, b)
            }*/
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}