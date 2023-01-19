package com.elshafee.androiden.breakingbadapp.db

import android.content.Context
import androidx.room.*
import com.elshafee.androiden.breakingbadapp.model.BreakingBadCharacter


@Database(
    entities = [BreakingBadCharacter::class],
    version = 1
)
@TypeConverters(AppTypeConverters::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun createCharacterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null
        fun createDatabase(context: Context): CharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "breakingbaddb.dp"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}