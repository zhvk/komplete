package com.zhvk.komplete.task_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.komplete.KompleteApplication
import com.zhvk.komplete.R
import com.zhvk.komplete.databinding.FragmentTaskListBinding
import kotlinx.coroutines.launch


const val CATEGORY_ID = "categoryId"
private const val TAG = "TaskListFragment"

class TaskListFragment : Fragment() {

    private var categoryId: Long = -1

    private val taskListViewModel: TaskListViewModel by activityViewModels {
        TaskListViewModelFactory(
            (activity?.application as KompleteApplication).database.allDao()
        )
    }

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            categoryId = it.getLong(CATEGORY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskListAdapter = TaskListAdapter(taskListViewModel)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = taskListViewModel

            recyclerView = taskListRecyclerView
            recyclerView.adapter = taskListAdapter

            newTaskDescription.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    taskListViewModel.addTask(
                        categoryId,
                        binding.newTaskName.text.toString(),
                        binding.newTaskDescription.text.toString()
                    )
                    true
                }
                false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            taskListViewModel.getCategoryOfTasks(categoryId).collect() {
                Log.d(TAG, "Observing: ${it.tasks}")
                binding.taskListTitle.text = it.category.title
                val remainingTasks = it.getRemainingTasks()
                binding.taskListSubtitle.text = resources.getQuantityString(
                    R.plurals.remaining_tasks,
                    remainingTasks,
                    remainingTasks
                )
                taskListAdapter.submitList(it.tasks)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}