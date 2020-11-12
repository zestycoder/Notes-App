package com.gnovack.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteListAdapter(
    private val myDataset: Array<Notes>,
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

        fun bind(note: Notes) {
            titleView?.text = note.title
            descView?.text = note.desc
            dateView?.text = note.date.toString()
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