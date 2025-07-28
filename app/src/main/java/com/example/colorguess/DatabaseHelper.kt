package com.example.colorguess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlin.compareTo

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "ColorGameDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT unique,
                email TEXT UNIQUE,
                password TEXT,
                score INTEGER DEFAULT 0
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun registerUser(name: String, email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
            put("password", password)
        }
        return db.insert("users", null, values) != -1L
    }

    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE (email = ? COLLATE NOCASE or name = ? COLLATE NOCASE) AND password = ?",
            arrayOf(email, email, password)
        )
        val result = cursor.count > 0
        cursor.close()
        return result
    }

    fun getUserNameByEmail(email: String): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM users WHERE lower(email) = lower(?) or lower(name) = lower(?)", arrayOf(email, email))
        var name = ""
        if (cursor.moveToFirst()) {
            name = cursor.getString(0)
        }
        cursor.close()
        return name
    }

    fun updateUserScore(name: String, score: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("score", score)
        val result = db.update("users", values, "name = ?", arrayOf(name))
        db.close()
        return result > 0
    }

    fun readUsers() {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                val score = cursor.getInt(cursor.getColumnIndexOrThrow("score"))

                android.util.Log.d("DB_USERS", "ID: $id, Name: $name, Email: $email, Password: $password, Score: $score")
            } while (cursor.moveToNext())
        } else {
            android.util.Log.d("DB_USERS", "No users found")
        }

        cursor.close()
    }

    fun updateScore(email: String, newScore: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("score", newScore)
        }
        db.update("users", values, "email = ?", arrayOf(email))
    }

    fun getUserScore(userName: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT score FROM users WHERE name = ?", arrayOf(userName))
        val score = if (cursor.moveToFirst()) cursor.getInt(0) else 0
        cursor.close()
        return score
    }
}