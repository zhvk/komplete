package com.zhvk.komplete

import android.app.Application
import com.zhvk.komplete.database.AppDatabase

class KompleteApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}