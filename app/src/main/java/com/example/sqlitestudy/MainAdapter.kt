package com.example.sqlitestudy

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainAdapter(val context : Context, var list : ArrayList<Student>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var db : MySQLiteOpenHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewGroup: Int): MainViewHolder {
        db = MySQLiteOpenHelper(context, "student.db", null, 1)
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.bind(list[position])

    fun setItems(data : ArrayList<Student>) {
        list = data
    }

    inner class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvName = (itemView).findViewById<TextView>(R.id.tv_name)
        val tvAge = (itemView).findViewById<TextView>(R.id.tv_age)
        val tvAddress = (itemView).findViewById<TextView>(R.id.tv_address)
        val rowLayout = (itemView).findViewById<LinearLayout>(R.id.rowLayout)

        fun bind(student : Student) {
            tvName.text = student.name
            tvAge.text = student.age
            tvAddress.text = student.address

            rowLayout.setOnClickListener(this)
        }

//        override fun onClick(v: View?) {
//

        override fun onClick(v: View?) {
            val selectDialog = AlertDialog.Builder(context)
            val selectArray = arrayOf("삭제", "수정")

            selectDialog.setTitle("작업을 선택하세요.")
                .setItems(selectArray) { dialog, which ->
                    when (which) {
                        0 -> {
                            // TODO : add "delete db"
                        }
                        1 -> {
                            // TODO : add "modify dialog"
                        }
                        else -> Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }

            selectDialog.show()
        }
    }
}