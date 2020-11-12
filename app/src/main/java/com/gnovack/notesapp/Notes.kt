package com.gnovack.notesapp

import java.util.*

data class Notes(val title: String, val desc: String, val date: Date, val id: Int) {
    lateinit var content: String
}