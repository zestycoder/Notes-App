package com.gnovack.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_first.*
import java.sql.Date

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val noteArr = arrayOf(
        Notes("One", "Hello", Date.valueOf("2000-8-28"), 1),
        Notes("Two", "World", Date.valueOf("2020-11-9"), 2), Notes(
            "Three", "Bad",
            Date.valueOf("2197-12-25"), 3
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
        }

        view.findViewById<FloatingActionButton>(R.id.addNote).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}
