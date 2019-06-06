package com.example.sqlitestudy

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

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

        override fun onClick(v: View?) {
            val selectDialog = AlertDialog.Builder(context)
            val selectArray = arrayOf("삭제", "수정")

            selectDialog.setTitle("작업을 선택하세요.")
                .setItems(selectArray) { _, which ->
                    when (which) {
                        0 -> {
                            // TODO : add "delete db"
                        }
                        1 -> {
                            val modifyDialog = AlertDialog.Builder(context)
                            val controlModifyDialog = modifyDialog.create()
                            val inflater = LayoutInflater.from(context).inflate(R.layout.dialog_modify, null)
                            controlModifyDialog.setTitle("데이터 수정")
                            controlModifyDialog.setView(inflater)
                            controlModifyDialog.show()

                            inflater.findViewById<EditText>(R.id.dialog_nameEditText).setText(list[adapterPosition].name)
                            inflater.findViewById<EditText>(R.id.dialog_ageEditText).setText(list[adapterPosition].age)
                            inflater.findViewById<EditText>(R.id.dialog_addressEditText).setText(list[adapterPosition].address)
                            val modifyButton = inflater.findViewById<Button>(R.id.dialog_modifyButton)

                            modifyButton.setOnClickListener {
                                val nameData = inflater.findViewById<EditText>(R.id.dialog_nameEditText).text.toString()
                                val ageData = inflater.findViewById<EditText>(R.id.dialog_ageEditText).text.toString()
                                val addressData = inflater.findViewById<EditText>(R.id.dialog_addressEditText).text.toString()

                                val modifyData = arrayListOf(nameData, ageData, addressData)
                                db.updateData(adapterPosition + 1, modifyData)

                                list[adapterPosition] = Student(nameData, ageData, addressData)
                                controlModifyDialog.dismiss()
                                notifyDataSetChanged()
                            }
                        }
                        else -> Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }

            selectDialog.show()
        }
    }
}