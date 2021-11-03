package com.najwan160.belajarretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ModelAdapter(private val dataWaktu: MutableList<String>, private val dataSubuh: MutableList<String>,
                   private val dataDzuhur: MutableList<String>, private val dataAshar: MutableList<String>,
                   private val dataMaghrib: MutableList<String>, private val dataIsya: MutableList<String>) :
    RecyclerView.Adapter<ModelAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textWaktu: TextView = view.findViewById(R.id.Tanggal)
        val textSubuh: TextView = view.findViewById(R.id.Subuh)
        val textDzuhur: TextView = view.findViewById(R.id.Dzuhur)
        val textAshar: TextView = view.findViewById(R.id.Ashar)
        val textMaghrib: TextView = view.findViewById(R.id.Maghrib)
        val textIsya: TextView = view.findViewById(R.id.Isya)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.model_rv_main, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textWaktu.text = dataWaktu[position]
        viewHolder.textSubuh.text = dataSubuh[position]
        viewHolder.textDzuhur.text = dataDzuhur[position]
        viewHolder.textAshar.text = dataAshar[position]
        viewHolder.textMaghrib.text = dataMaghrib[position]
        viewHolder.textIsya.text = dataIsya[position]
    }

    override fun getItemCount() = dataWaktu.size

}