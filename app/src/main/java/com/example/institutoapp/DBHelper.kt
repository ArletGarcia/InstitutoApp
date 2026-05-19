package com.example.institutoapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):
    SQLiteOpenHelper(context,"Instituto.db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(
            "CREATE TABLE asistencia(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "materia TEXT," +
                    "asistencia TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS asistencia")
        onCreate(db)
    }

    fun insertar(nombre:String,materia:String,asistencia:String):Boolean{

        val db = this.writableDatabase
        val values = ContentValues()

        values.put("nombre",nombre)
        values.put("materia",materia)
        values.put("asistencia",asistencia)

        val resultado = db.insert("asistencia",null,values)

        return resultado != -1L
    }

    fun consultar(): Cursor {

        val db = this.readableDatabase

        return db.rawQuery(
            "SELECT * FROM asistencia",
            null
        )
    }
}