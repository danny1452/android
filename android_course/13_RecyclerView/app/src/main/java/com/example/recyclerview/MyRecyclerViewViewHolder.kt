package com.example.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(student: Student) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val city = itemView.findViewById<TextView>(R.id.city)

        name.text = student.name
        city.text = student.city
    }
}