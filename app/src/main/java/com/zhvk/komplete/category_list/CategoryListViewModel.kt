package com.zhvk.komplete.category_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zhvk.komplete.database.AllDao
import com.zhvk.komplete.database.Category
import com.zhvk.komplete.database.CategoryOfTasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryListViewModel(
    private val allDao: AllDao
) : ViewModel() {

    val allCategoriesOfTasks: LiveData<List<CategoryOfTasks>> = allDao.loadCategoriesOfTasks().asLiveData()

    fun addEmptyCategory() {
        val category = Category(title = "New category")
        viewModelScope.launch(Dispatchers.IO) {
            allDao.insertCategory(category)
        }
    }
}

class CategoryListViewModelFactory(
    private val allDao: AllDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryListViewModel(allDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}