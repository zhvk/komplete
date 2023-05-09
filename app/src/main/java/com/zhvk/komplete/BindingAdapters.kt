package com.zhvk.komplete

import android.content.Context
import android.graphics.Paint
import android.view.View
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

@BindingAdapter("setAlphaBasedOnCompletedState")
fun setAlphaBasedOnCompletedState(layout: ConstraintLayout, taskCompleted: Boolean) {
    val alpha = if (taskCompleted) 0.5f else 1f
    layout.alpha = alpha
}

@BindingAdapter("context", "completedState")
fun setColorBasedOnCompletedState(view: View, context: Context, taskCompleted: Boolean) {
    val color = if (taskCompleted) context.getColor(R.color.gray2)
    else context.getColor(R.color.white)
    view.setBackgroundColor(color)
}

@BindingAdapter("context", "completedState")
fun setColorBasedOnCompletedState(textView: TextView, context: Context, taskCompleted: Boolean) {
    val color = if (taskCompleted) context.getColor(R.color.gray5)
    else context.getColor(R.color.black)
    textView.setTextColor(color)
}