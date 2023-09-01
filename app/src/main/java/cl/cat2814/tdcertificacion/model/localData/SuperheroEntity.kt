package cl.cat2814.tdcertificacion.model.localData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superheroes_table")
data class SuperheroEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val birthPlace: String,
    val imageLink: String,
    val superPower: String,
    val creationYear: Int
)
