package com.example.sqlitestudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var db : MySQLiteOpenHelper
    var dbList : ArrayList<Student> = arrayListOf()
    lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            db = MySQLiteOpenHelper(this, "student.db", null, 1)

        dbList = db.getAllData()
        adapter = MainAdapter(this, dbList)
        dbRecycler.adapter = adapter

        insertButton.setOnClickListener {
            if(nameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty() && addressEditText.text.isNotEmpty()) {
                val name = nameEditText.text.toString()
                val age = ageEditText.text.toString()
                val address = addressEditText.text.toString()

                db.insert(Student(name, age, address))
                adapter.setItems(db.getAllData())
                Toast.makeText(this, "데이터를 삽입했습니다.", Toast.LENGTH_LONG).show()

                nameEditText.text = null
                ageEditText.text = null
                addressEditText.text = null
            }
            else {
                Toast.makeText(this, "텍스트를 모두 채워주세요,", Toast.LENGTH_LONG).show()
            }
            adapter.notifyDataSetChanged()
        }

        clearButton.setOnClickListener {
            db.clearDB()
            Toast.makeText(this, "데이터를 모두 제거했습니다.", Toast.LENGTH_LONG).show()
            adapter.setItems(db.getAllData())
            adapter.notifyDataSetChanged()
        }
    }

}
