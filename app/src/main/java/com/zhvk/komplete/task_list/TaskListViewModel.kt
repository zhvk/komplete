package com.zhvk.komplete.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.zhvk.komplete.database.AllDao
import com.zhvk.komplete.database.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val allDao: AllDao,
) : ViewModel() {

    fun getCategoryOfTasks(categoryId: Long) = allDao.loadCategoryOfTasks(categoryId)

    fun getTasks(categoryId: Long) = allDao.loadTasksFromCategory(categoryId)

    fun addTask(parentCategoryId: Long, name: String, description: String) {
        val task = Task(
            parentCategoryId = parentCategoryId,
            title = name,
            description = description
        )
        viewModelScope.launch(Dispatchers.IO) {
            allDao.insertTask(task)
        }
    }

    fun completeTask(task: Task, isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            allDao.updateTask(task.copy(completed = isChecked))
        }
    }

    fun completeTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            allDao.updateTask(task.copy(completed = !task.completed))
        }
    }
}

class TaskListViewModelFactory(
    private val allDao: AllDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(allDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}