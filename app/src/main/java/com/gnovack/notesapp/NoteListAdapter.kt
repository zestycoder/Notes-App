package com.gnovack.notesapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteListAdapter(
    private val myDataset: ArrayList<Notes>,
    private val onItemClicked: (Notes) -> Unit
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    class NoteViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private var titleView: TextView? = null
        private var descView: TextView? = null
        private var dateView: TextView? = null
        private var numView: TextView? = null

        init {
            titleView = view.findViewById(R.id.title)
            descView = view.findViewById(R.id.description)
            dateView = view.findViewById(R.id.date)
            numView = view.findViewById(R.id.num)

            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(note: Notes) {
            val content = note.content.replace("\n", "")
            if (content.length > 50)
                descView?.text = "${content.substring(0, 20)}..."
            else
                descView?.text = content

            titleView?.text = note.title
            dateView?.text = note.date
            numView?.text = "#${note.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView) {
            onItemClicked(myDataset[it])
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = myDataset[position]
        holder.bind(currentNote)
    }
}