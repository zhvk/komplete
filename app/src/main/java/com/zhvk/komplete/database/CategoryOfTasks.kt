package com.zhvk.komplete.database

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryOfTasks(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "parentCategoryId"
    )
    val tasks: List<Task>
) {
    fun getNumberOfTasks(): String = tasks.size.toString()

    fun getCompletedTasks(): String = getCompletedTasksInt().toString()

    fun getPercentageDone(): String {
        return if (tasks.isNotEmpty())
            String.format("%.0f", getCompletedTasksInt() / tasks.size.toDouble() * 100.00)
        else "0"
    }

    private fun getCompletedTasksInt(): Int = tasks.filter { it.completed }.size
}
