package com.zhvk.komplete.database

import androidx.room.*

@Dao
interface AllDao {

    @Transaction
    @Query("SELECT * FROM category")
    fun loadCategoriesOfTasks(): List<CategoryOfTasks>

    @Query("SELECT * FROM task WHERE parentCategoryId=:id")
    fun loadTasksFromCategory(id: Int): List<Task>

    @Transaction
    @Insert
    suspend fun insertCategory(vararg category: Category)

    @Insert
    suspend fun insertTask(vararg task: Task)

    @Transaction
    @Delete
    suspend fun deleteCategory(category: Category)

    @Delete
    suspend fun deleteDask(task: Task)
}