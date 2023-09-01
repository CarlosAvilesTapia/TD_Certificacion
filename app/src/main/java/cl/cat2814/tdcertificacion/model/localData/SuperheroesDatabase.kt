package cl.cat2814.tdcertificacion.model.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuperheroEntity::class, SuperheroDetailEntity::class], version = 1)
abstract class SuperheroesDatabase: RoomDatabase() {

    abstract fun getSuperheroesFromDao(): SuperheroDao

    companion object {
        @Volatile
        private var INSTANCE: SuperheroesDatabase? = null

        fun getDatabase(context: Context): SuperheroesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperheroesDatabase::class.java,
                    "superheroes_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
