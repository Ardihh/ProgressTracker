package com.example.progresstracker.data

data class Progress(
    val id: Int = 0,
    val namaMahasiswa: String,
    val totalSks: Int,
    val sksLulus: Int,
    val ipk: Double
)