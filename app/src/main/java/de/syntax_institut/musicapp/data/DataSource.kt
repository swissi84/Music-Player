package de.syntax_institut.musicapp.data

import de.syntax_institut.musicapp.R


val songList = listOf(
    Song("The Beatles", "Hey Jude", 431, R.drawable.user_pic),
    Song("Queen", "Bohemian Rhapsody", 354, R.drawable.user_pic),
    Song("Michael Jackson", "Thriller", 357, R.drawable.user_pic),
    Song("Adele", "Rolling in the Deep", 228, R.drawable.user_pic)
)

val Song.formattedLength: String
    get() {
        val minutes = length / 60
        val seconds = length % 60
        return "$minutes:${seconds.toString().padStart(2, '0')}"
    }
