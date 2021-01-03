package com.example.retrofitkotlin.RetrofitGetRecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import kotlinx.android.synthetic.main.spinner_item.view.*

class CountryAdapter(private  val countries: List<CountryItem>?): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spinner_item,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount()= countries!!.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryPosition = countries?.get(position)

            Log.d("Ankan", "Code? ${countryPosition?.country_code}")

        holder.code.text = countryPosition!!.country_code
        holder.flag.text = countryPosition.flag
    }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val code: TextView = itemView.code
        val flag: TextView = itemView.flag
    }
}