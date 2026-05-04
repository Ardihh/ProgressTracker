package com.example.progresstracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progresstracker.R
import com.example.progresstracker.adapter.ProgressAdapter
import com.example.progresstracker.data.DatabaseHelper

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProgressAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recyclerViewProgress)
        databaseHelper = DatabaseHelper(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val progressList = databaseHelper.getAllProgress()
        adapter = ProgressAdapter(progressList)
        recyclerView.adapter = adapter
    }
}