package com.minhoi.forlivingalone.board

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.BoarditemBinding

class BoardAdapter(private val data : ArrayList<BoardContent>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    class ViewHolder(binding : BoarditemBinding) : RecyclerView.ViewHolder(binding.root) {

        val title : TextView = binding.boardTitle
        val content : TextView = binding.boardContent
        val time : TextView = binding.writeDate

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = DataBindingUtil.inflate<BoarditemBinding>(LayoutInflater.from(parent.context),
            R.layout.boarditem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.content.text = data[position].content
        holder.time.text = data[position].date
    }
}