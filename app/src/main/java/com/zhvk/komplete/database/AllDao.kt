package com.zhvk.komplete.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AllDao {

    @Transaction
    @Query("SELECT * FROM category")
    fun loadCategoriesOfTasks(): Flow<List<CategoryOfTasks>>

    @Transaction
    @Query("SELECT * FROM category WHERE categoryId=:id")
    fun loadCategoryOfTasks(id: Long): Flow<CategoryOfTasks>

    @Query("SELECT * FROM task WHERE parentCategoryId=:id")
    fun loadTasksFromCategory(id: Long): Flow<List<Task>>

    @Query("SELECT * FROM category WHERE categoryId=:id")
    fun getCategory(id: Long): Flow<Category>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(vararg category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(vararg task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Transaction
    @Delete
    suspend fun deleteCategory(category: Category)

    @Delete
    suspend fun deleteDask(task: Task)
}