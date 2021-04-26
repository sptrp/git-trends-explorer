package com.ivanponomarev.presentation.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R

class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        private var data = emptyList<Repo?>()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.text_item_view)
            val imageView: SimpleDraweeView = view.findViewById(R.id.repo_avatar)

        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)

            val view = inflater.inflate(R.layout.repo_card, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.textView.text = data[position]?.name

            val avatarURI = data[position]?.avatar
            viewHolder.imageView.setImageURI(avatarURI)

            viewHolder.imageView.setOnClickListener {

            }

        }

        override fun getItemCount(): Int = data.size

        fun setData(newData: List<Repo?>) {
            data = newData
            notifyDataSetChanged()
        }
}