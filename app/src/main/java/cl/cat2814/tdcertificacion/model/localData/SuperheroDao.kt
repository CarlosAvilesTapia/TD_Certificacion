package cl.cat2814.tdcertificacion.model.localData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperheroDao {

    //Inserción y consulta de la lista de superhéroes, la que también se testeará
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperheroes(superheroEntity: List<SuperheroEntity>)

    @Query("SELECT * FROM superheroes_table ORDER BY id ASC")
    fun getSuperheroesListFromDatabase(): LiveData<List<SuperheroEntity>>



    // Inserción y consulta de detalles del superhéroe desde la Database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperheroDetail(superheroDetailEntity: SuperheroDetailEntity)

    @Query("SELECT * FROM superheroes_detail_table WHERE id = :id")
    fun getSuperheroDetailFromDatabase(id: Int): LiveData<SuperheroDetailEntity>
}
