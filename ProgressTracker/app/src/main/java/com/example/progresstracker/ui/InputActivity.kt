package com.example.progresstracker.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.progresstracker.R
import com.example.progresstracker.data.DatabaseHelper
import com.example.progresstracker.data.Progress

class InputActivity : AppCompatActivity() {

    private lateinit var etNamaMahasiswa: EditText
    private lateinit var etTotalSks: EditText
    private lateinit var etSksLulus: EditText
    private lateinit var etIpk: EditText
    private lateinit var btnSimpan: Button
    private lateinit var btnLihatData: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        etNamaMahasiswa = findViewById(R.id.etNamaMahasiswa)
        etTotalSks = findViewById(R.id.etTotalSks)
        etSksLulus = findViewById(R.id.etSksLulus)
        etIpk = findViewById(R.id.etIpk)
        btnSimpan = findViewById(R.id.btnSimpan)
        btnLihatData = findViewById(R.id.btnLihatData)

        databaseHelper = DatabaseHelper(this)

        btnSimpan.setOnClickListener {
            saveProgress()
        }

        btnLihatData.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }

    private fun saveProgress() {
        val namaMahasiswa = etNamaMahasiswa.text.toString().trim()
        val totalSksText = etTotalSks.text.toString().trim()
        val sksLulusText = etSksLulus.text.toString().trim()
        val ipkText = etIpk.text.toString().trim()

        if (namaMahasiswa.isEmpty() || totalSksText.isEmpty() || sksLulusText.isEmpty() || ipkText.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val progress = Progress(
            namaMahasiswa = namaMahasiswa,
            totalSks = totalSksText.toInt(),
            sksLulus = sksLulusText.toInt(),
            ipk = ipkText.toDouble()
        )

        val isInserted = databaseHelper.insertProgress(progress)

        if (isInserted) {
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            clearFields()
        } else {
            Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields() {
        etNamaMahasiswa.text.clear()
        etTotalSks.text.clear()
        etSksLulus.text.clear()
        etIpk.text.clear()
    }
}