package com.example.deeze_project.view

import android.view.View

interface ClickListener {
    fun <T> onItemClick(view: View, data: T)
}