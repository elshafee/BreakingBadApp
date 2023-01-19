package com.elshafee.androiden.breakingbadapp.repositry

import androidx.lifecycle.LiveData
import com.elshafee.androiden.breakingbadapp.db.CharacterDao
import com.elshafee.androiden.breakingbadapp.model.BreakingBadCharacter
import com.elshafee.androiden.breakingbadapp.services.BreakingBadNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {
    //GETTING LIST FROM LOCAL DB
    var characters:LiveData<List<BreakingBadCharacter>> = characterDao.findAllCharacters()


    suspend fun refreshCharacter() {
        withContext(Dispatchers.IO) {
            val characters =
                BreakingBadNetwork.serviceApi.getCharacters() //GETTING ALL DATA FROM API
            characterDao.insertAllCharacters(characters) //INSERT DATA TO  LOCAL DATABASE
        }
    }
}