package com.gnovack.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_second.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    var isNewNote = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var noteToEdit: Notes? = null

        setFragmentResultListener("IS_NEW_OR_NOT") { _, bundle ->
            isNewNote = false
            noteToEdit = bundle.get("EDIT_NOTE") as Notes
            title.setText(noteToEdit!!.title)
            content.setText(noteToEdit!!.content)
        }

        view.findViewById<FloatingActionButton>(R.id.done_edit).setOnClickListener {
            val titleText = title.text.toString()
            val contText = content.text.toString()
            val currentTime = Calendar.getInstance().time
            val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = df.format(currentTime)
            val id = noteToEdit?.id ?: -1

            val newNote = Notes(titleText, contText, date, id)
            setFragmentResult(
                "note_info",
                bundleOf(Pair("NOTE", newNote), Pair("IS_NEW", isNewNote))
            )
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
