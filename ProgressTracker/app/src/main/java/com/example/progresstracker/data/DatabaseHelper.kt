package com.example.progresstracker.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "progress_tracker.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "progress"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAMA_MAHASISWA = "nama_mahasiswa"
        private const val COLUMN_TOTAL_SKS = "total_sks"
        private const val COLUMN_SKS_LULUS = "sks_lulus"
        private const val COLUMN_IPK = "ipk"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAMA_MAHASISWA TEXT NOT NULL,
                $COLUMN_TOTAL_SKS INTEGER NOT NULL,
                $COLUMN_SKS_LULUS INTEGER NOT NULL,
                $COLUMN_IPK REAL NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertProgress(progress: Progress): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAMA_MAHASISWA, progress.namaMahasiswa)
            put(COLUMN_TOTAL_SKS, progress.totalSks)
            put(COLUMN_SKS_LULUS, progress.sksLulus)
            put(COLUMN_IPK, progress.ipk)
        }

        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun getAllProgress(): ArrayList<Progress> {
        val progressList = ArrayList<Progress>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC", null)

        if (cursor.moveToFirst()) {
            do {
                val progress = Progress(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    namaMahasiswa = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_MAHASISWA)),
                    totalSks = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_SKS)),
                    sksLulus = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SKS_LULUS)),
                    ipk = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_IPK))
                )
                progressList.add(progress)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return progressList
    }
}