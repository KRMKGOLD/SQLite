package com.example.sqlitestudy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainAdapter(val context : Context, var list : ArrayList<Student>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewGroup: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.bind(list[position])

    fun setItems(data : ArrayList<Student>) {
        list = data
    }

    inner class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvName = (itemView).findViewById<TextView>(R.id.tv_name)
        val tvAge = (itemView).findViewById<TextView>(R.id.tv_age)
        val tvAddress = (itemView).findViewById<TextView>(R.id.tv_address)

        fun bind(student : Student) {
            tvName.text = student.name
            tvAge.text = student.age
            tvAddress.text = student.address
        }
    }
}