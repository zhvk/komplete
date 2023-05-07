package com.zhvk.komplete.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0,

    val title: String?
)
