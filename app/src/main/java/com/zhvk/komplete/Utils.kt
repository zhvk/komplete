package com.zhvk.komplete

import android.graphics.Color

class Utils {
    companion object {
        fun getRandomColors(): Int {
            return listOf(
                Color.parseColor("#264653"),
                Color.parseColor("#2A9D8F"),
                Color.parseColor("#E9C46A"),
                Color.parseColor("#F4A261"),
                Color.parseColor("#E76F51"),
                //Color.parseColor("#FFFFFF")
                ).random()
        }
    }
}