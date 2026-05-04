package com.example.progresstracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.progresstracker.R
import com.example.progresstracker.data.Progress

class ProgressAdapter(private val progressList: ArrayList<Progress>) :
    RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaMahasiswa: TextView = itemView.findViewById(R.id.tvNamaMahasiswa)
        val tvSks: TextView = itemView.findViewById(R.id.tvSks)
        val tvIpk: TextView = itemView.findViewById(R.id.tvIpk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_progress, parent, false)
        return ProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val progress = progressList[position]

        holder.tvNamaMahasiswa.text = progress.namaMahasiswa
        holder.tvSks.text = "${progress.sksLulus} / ${progress.totalSks} SKS"
        holder.tvIpk.text = "IPK: ${progress.ipk}"
    }

    override fun getItemCount(): Int = progressList.size
}