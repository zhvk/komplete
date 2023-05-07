package com.zhvk.komplete.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0,

    val parentCategoryId: Int,
    val title: String?,
    val description: String?
)
