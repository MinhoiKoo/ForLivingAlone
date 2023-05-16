package com.minhoi.forlivingalone.board

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.utils.Ref

class CommentListAdapter (private val commentList : MutableList<CommentData>, private val commentKeyList : MutableList<String>) : BaseAdapter() {

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

        val btn = view?.findViewById<ImageButton>(R.id.commentDialogBtn)
        btn?.setOnClickListener {
            showPopupMenu(btn, position)
        }

        return view!!
    }

    private fun showPopupMenu(anchorView: View, position: Int) {
        val popupMenu = PopupMenu(anchorView.context, anchorView)
        popupMenu.inflate(R.menu.comment_popup_menu)

        // 팝업 메뉴 아이템 클릭 이벤트 처리
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.commentDelete -> {
                    // Delete 메뉴 아이템 클릭 처리
                    // position을 기반으로 해당 아이템의 삭제 작업을 수행
                    // TODO: 삭제 작업 처리
                    Ref.commentRef.child(commentList[position].boardKey).child(commentKeyList[position]).removeValue()
                    Log.d("comment", commentKeyList[position])
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}