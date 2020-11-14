package com.gnovack.notesapp

import java.io.Serializable

data class Notes(val title: String, val content: String, val date: String, var id: Int) :
    Serializable