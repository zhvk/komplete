package com.zhvk.komplete

import android.graphics.Paint
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView, strikeThrough: Boolean) {
    if (strikeThrough) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("alphaEnabled")
fun setAlphaBasedOnEnabledState(layout: ConstraintLayout, taskCompleted: Boolean) {
    if (taskCompleted) {
        layout.alpha = 0.25f
    } else {
        layout.alpha = 1f
    }
}