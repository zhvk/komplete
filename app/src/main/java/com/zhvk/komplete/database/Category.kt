package com.zhvk.komplete.database

import android.graphics.drawable.ColorDrawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhvk.komplete.Utils

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0,

    val title: String?,
    val color: Int = Utils.getRandomColors()
) {
    fun getColorDrawable() : ColorDrawable {
        return ColorDrawable(color)
    }
}
