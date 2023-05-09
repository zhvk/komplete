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
    fun getNumberOfTasks(): Int = tasks.size

    fun getCompletedTasks(): Int = tasks.filter { it.completed }.size

    fun getRemainingTasks(): Int = tasks.filter { !it.completed }.size

    fun getPercentageDone(): String {
        return if (tasks.isNotEmpty())
            String.format("%.0f", getCompletedTasks() / tasks.size.toDouble() * 100.00)
        else "0"
    }
}
