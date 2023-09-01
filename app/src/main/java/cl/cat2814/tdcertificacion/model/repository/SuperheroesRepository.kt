package cl.cat2814.tdcertificacion.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import cl.cat2814.tdcertificacion.model.localData.SuperheroDao
import cl.cat2814.tdcertificacion.model.localData.SuperheroDetailEntity
import cl.cat2814.tdcertificacion.model.localData.SuperheroEntity
import cl.cat2814.tdcertificacion.model.remoteData.SuperheroApi

class SuperheroesRepository(
private val superheroApi: SuperheroApi,
private val superheroDao: SuperheroDao
) {
    // Función para cargar listado de superhéroes en la database.
    suspend fun loadSuperheroesFromApiToDatabase() {
        try {
            val response = superheroApi.getSuperheroesFromApi()
            if (response.isSuccessful) {
                val responseSuperheroes = response.body()
                responseSuperheroes?.let {
                    val superheroesEntities = it.map { it.toEntity() }
                    superheroDao.insertSuperheroes(superheroesEntities)

                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("Repository", exception.toString())
        }
    }

    // Función para obtener listado de superhéroes.
    fun getSuperheroesFromDatabase(): LiveData<List<SuperheroEntity>> =
        superheroDao.getSuperheroesListFromDatabase()


    // Función para cargar detalle de superhéroe en la database.
    suspend fun loadSuperheroDetailFromApiToDatabase(id : Int){
        try {
            val response = superheroApi.getSuperheroesDetailFromApi(id)
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val superheroDetailEntity = it.toDetailEntity()
                    superheroDao.insertSuperheroDetail(superheroDetailEntity)
                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("Repository","")
        }
    }

    // Función para obtener detalle de superhéroe.
    fun getSuperheroDetailFromDatabase(id: Int): LiveData<SuperheroDetailEntity> =
        superheroDao.getSuperheroDetailFromDatabase(id)
}
