package com.example.sqlitestudy

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.annotations.TestOnly


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
            val tempStudent = Student(null, null, null)
            tempStudent.name = cursor.getString(1)
            tempStudent.age = cursor.getString(2)
            tempStudent.address = cursor.getString(3)

            tempArrayList.add(tempStudent)
        }

        return tempArrayList
    }

    fun selectData(id : Int) : Student {
        val db = writableDatabase
        val cursor = db.rawQuery("Select * from student where _id = $id", null)
        val tempStudent = Student(null, null, null)

        cursor.moveToNext()
        tempStudent.name = cursor.getString(1)
        tempStudent.age = cursor.getString(2)
        tempStudent.address = cursor.getString(3)

        return tempStudent
    }

    fun clearDB() {
        val db = writableDatabase
        db.execSQL("Drop table if exists student")
        onCreate(db)
    }
}