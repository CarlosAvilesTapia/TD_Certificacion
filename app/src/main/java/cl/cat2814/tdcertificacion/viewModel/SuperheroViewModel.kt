package cl.cat2814.tdcertificacion.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.cat2814.tdcertificacion.model.localData.SuperheroesDatabase
import cl.cat2814.tdcertificacion.model.remoteData.SuperheroClient
import cl.cat2814.tdcertificacion.model.repository.SuperheroesRepository
import kotlinx.coroutines.launch

class SuperheroesViewModel(application: Application) : AndroidViewModel(application) {

    private val superheroesRepository: SuperheroesRepository

    // Funciones para el listado de superhéroes
    fun liveDataSuperheroesFromRepository() = superheroesRepository.getSuperheroesFromDatabase()

    init {
        val superheroesApi = SuperheroClient.getRetrofitSuperhero()
        val superheroesDatabase =
            SuperheroesDatabase.getDatabase(application).getSuperheroesFromDao()
        superheroesRepository = SuperheroesRepository(superheroesApi, superheroesDatabase)
    }

    fun getAllSuperheroesFromRepository() =
        viewModelScope.launch { superheroesRepository.loadSuperheroesFromApiToDatabase() }

    // Funciones para el detalle de superhéroe
    fun liveDataSuperheroDetailFromRepository(id: Int) =
        superheroesRepository.getSuperheroDetailFromDatabase(id)

    fun getSuperheroeDetailFromRepository(id: Int) =
        viewModelScope.launch { superheroesRepository.loadSuperheroeDetailFromApiToDatabase(id) }
}
