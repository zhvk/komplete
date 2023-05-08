package com.zhvk.komplete.category_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.zhvk.komplete.KompleteApplication
import com.zhvk.komplete.R
import com.zhvk.komplete.databinding.FragmentCategoryListBinding
import javax.sql.DataSource

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
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryListAdapter {
//            val action = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(itemId =  it.id, kupus = "123")
//            view.findNavController().navigate(action)
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