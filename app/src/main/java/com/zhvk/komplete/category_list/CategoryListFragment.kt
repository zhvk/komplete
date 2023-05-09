package com.zhvk.komplete.category_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.zhvk.komplete.KompleteApplication
import com.zhvk.komplete.R
import com.zhvk.komplete.databinding.FragmentCategoryListBinding

private const val TAG = "CategoryListFragment"

class CategoryListFragment : Fragment() {

    private val categoryListViewModel: CategoryListViewModel by activityViewModels {
        CategoryListViewModelFactory(
            (activity?.application as KompleteApplication).database.allDao()
        )
    }

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_category_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryListAdapter {
            val action =
                CategoryListFragmentDirections.actionCategoryListFragmentToTaskListFragment(
                    categoryId = it.category.categoryId
                )
            view.findNavController().navigate(action)
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@CategoryListFragment

            categoryListRecyclerView.adapter = adapter
        }

        categoryListViewModel.allCategoriesOfTasks.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createEmptyCategory() {
        categoryListViewModel.addEmptyCategory()
    }
}