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
)
