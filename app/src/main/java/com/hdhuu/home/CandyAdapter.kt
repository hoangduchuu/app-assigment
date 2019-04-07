package com.hdhuu.home

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.hdhuu.R
import com.hdhuu.models.Candy
import kotlinx.android.synthetic.main.candy_item.view.*

/**
 * Created by Huu Hoang on 3/31/19.
 */

class CandyAdapter(var candies: ArrayList<Candy>, val context: Context) :
    RecyclerView.Adapter<CandyAdapter.ViewHolder>() {
    lateinit var mListener: CandyItemClickListener

    // Gets the number of datas in the list
    override fun getItemCount(): Int {
        return candies.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.candy_item, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.counter.text = candies[position].eatingCount.toString()
        holder.bg.setBackgroundColor(candies[position].color!!)
        holder.itemView.setOnClickListener {
            mListener.onItemClicked(candies[position],position)
        }
    }

    fun setdata(data: ArrayList<Candy>) {
        candies = data
    }

    fun setListener(listener: CandyItemClickListener) {
        this.mListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val counter = view.tvCounter
        val bg = view.rootItem
    }

}
