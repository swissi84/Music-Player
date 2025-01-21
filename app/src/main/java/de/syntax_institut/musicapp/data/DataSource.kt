package de.syntax_institut.musicapp.data

import de.syntax_institut.musicapp.R


val songList = listOf(
    Song("The Beatles", "Hey Jude", 431, R.drawable.beatles),
    Song("Queen", "Bohemian Rhapsody", 354, R.drawable.queen),
    Song("Michael Jackson", "Thriller", 357, R.drawable.jackson),
    Song("Adele", "Rolling in the Deep", 228, R.drawable.adele),
    Song("The Beatles", "Hey Jude", 431, R.drawable.beatles),
    Song("Queen", "Bohemian Rhapsody", 354, R.drawable.queen),
    Song("Michael Jackson", "Thriller", 357, R.drawable.jackson),
    Song("Adele", "Rolling in the Deep", 228, R.drawable.adele),
    Song("The Beatles", "Hey Jude", 431, R.drawable.beatles),
    Song("Queen", "Bohemian Rhapsody", 354, R.drawable.queen),
    Song("Michael Jackson", "Thriller", 357, R.drawable.jackson),
    Song("Adele", "Rolling in the Deep", 228, R.drawable.adele),
    Song("The Beatles", "Hey Jude", 431, R.drawable.beatles),
    Song("Queen", "Bohemian Rhapsody", 354, R.drawable.queen),
    Song("Michael Jackson", "Thriller", 357, R.drawable.jackson),
    Song("Adele", "Rolling in the Deep", 228, R.drawable.adele),

)

val Song.formattedLength: String
    get() {
        val minutes = length / 60
        val seconds = length % 60
        return "$minutes:${seconds.toString().padStart(2, '0')}"
    }
