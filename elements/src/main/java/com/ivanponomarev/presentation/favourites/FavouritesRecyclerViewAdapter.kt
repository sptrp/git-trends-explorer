package com.ivanponomarev.presentation.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R

class FavouritesRecyclerViewAdapter(val showDetailsFragment: (Repo?) -> Unit) :
    RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder>() {

        private var data = emptyList<Repo?>()
        lateinit var mRecyclerView: RecyclerView

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.text_item_view)
            val imageView: SimpleDraweeView = view.findViewById(R.id.repo_avatar)

        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)

            val view = inflater.inflate(R.layout.repo_card, viewGroup, false)

            // Return ViewHolder and showDetailsFragment on click
            return ViewHolder(view).listen { pos, _ ->
                val item = data[pos]
                showDetailsFragment(item)
            }
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.textView.text = data[position]?.name

            val avatarURI = data[position]?.avatar
            viewHolder.imageView.setImageURI(avatarURI)

        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)

            mRecyclerView = recyclerView
        }

        fun setData(newData: List<Repo?>) {
            data = newData
            notifyDataSetChanged()
        }

        // Extend ViewHolder with listener
        private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
            itemView.setOnClickListener {
                event.invoke(adapterPosition, itemViewType)
            }

            return this
        }

        override fun getItemCount(): Int = data.size
}