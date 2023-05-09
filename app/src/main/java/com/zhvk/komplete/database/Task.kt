package com.zhvk.komplete.database

import android.graphics.drawable.ColorDrawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhvk.komplete.Utils

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0,

    val parentCategoryId: Long,
    val title: String?,
    val description: String?,
    val completed: Boolean = false,
    val color: Int = Utils.getRandomColors()
) {
    fun getColorDrawable() : ColorDrawable {
        return ColorDrawable(color)
    }
}