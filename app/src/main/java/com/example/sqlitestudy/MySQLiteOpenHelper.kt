package com.example.sqlitestudy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MySQLiteOpenHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    private val TAG = "MySQLiteOpenHelper"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table student (_id integer primary key autoincrement, name text, age integer, address text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("Drop table if exists student")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insert(student : Student) {
        val db = writableDatabase

        val values = ContentValues()
        values.put("name", student.name)
        values.put("age", student.age)
        values.put("address", student.address)
        db.insert("student", null, values)
    }

    fun getAllData() : ArrayList<Student> {
        val db = readableDatabase
        val cursor = db.query("student", null, null, null, null, null, null)
        val tempArrayList : ArrayList<Student> = arrayListOf()

        while(cursor.moveToNext()) {
            val tempName = cursor.getString(1)
            val tempAge = cursor.getString(2)
            val tempAddress = cursor.getString(3)

            val tempStudent = Student(tempName, tempAge, tempAddress)
            tempArrayList.add(tempStudent)
        }
       return tempArrayList
    }

    fun updateData(id : Int, data : ArrayList<String>) {
        val db = writableDatabase
        db.execSQL("""update student set name = '${data[0]}', age = '${data[1]}', address = '${data[2]}' where _id = $id""")
    }

    fun deleteData(id : Int) {
        val db = writableDatabase
        db.execSQL("Delete from student where _id = $id")
        db.execSQL("update student set _id = _id - 1 where _id > $id")
    }

    fun clearDB() {
        val db = writableDatabase
        db.execSQL("Drop table if exists student")
        onCreate(db)
    }
}