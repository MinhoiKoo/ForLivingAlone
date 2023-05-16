package com.minhoi.forlivingalone.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.minhoi.forlivingalone.R

class CommentListAdapter (private val commentList : MutableList<CommentData>) : BaseAdapter() {

    override fun getCount(): Int {
        return commentList.size
    }

    override fun getItem(position: Int): Any {
        return commentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.comment_list_item, null)
        }
        val content = view?.findViewById<TextView>(R.id.commentContent)
        val time = view?.findViewById<TextView>(R.id.commentWriteTime)

        content?.text = commentList[position].content
        time?.text = commentList[position].writeTime

        return view!!
    }
}