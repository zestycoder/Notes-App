package com.gnovack.notesapp

data class Notes(val title: String, val desc: String, val date: String, val id: Int) {
    lateinit var content: String
}