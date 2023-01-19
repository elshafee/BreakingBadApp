package com.elshafee.androiden.breakingbadapp

import android.app.Application
import com.elshafee.androiden.breakingbadapp.db.CharacterDatabase
import com.elshafee.androiden.breakingbadapp.repositry.CharacterRepository

class BreakingBadApplication :Application() {

    val database by lazy {
        CharacterDatabase.createDatabase(this)
    }

    val characterRepositry by lazy {
        CharacterRepository(database.createCharacterDao())
    }
}