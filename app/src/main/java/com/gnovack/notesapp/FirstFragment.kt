package com.gnovack.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_first.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var noteArr: ArrayList<Notes> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteArr = arrayListOf(
            Notes("One", "Hello", "2000-8-28", 1),
            Notes("Two", "World", "2020-11-9", 2),
            Notes("Three", "Bad", "2197-12-25", 3)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linLayoutManager = LinearLayoutManager(view.context)
        noteList.apply {
            layoutManager = linLayoutManager
            adapter = NoteListAdapter(noteArr) { note ->
                val toast = Toast.makeText(view.context, "Note #${note.id}", Toast.LENGTH_SHORT)
                toast.show()
            }

            setFragmentResultListener("note_info") { key, bundle ->
                val noteTitle = bundle.getString("TITLE")
                val noteText = bundle.getString("TEXT")
                val id = noteArr.size + 1
                val currentTime = Calendar.getInstance().time
                val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = df.format(currentTime)

                noteArr.add(Notes(noteTitle ?: "Unknown", noteText ?: "Unknown", date, id))
                noteList.adapter?.notifyItemInserted(noteArr.lastIndex)
            }
        }

        view.findViewById<FloatingActionButton>(R.id.addNote).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}
