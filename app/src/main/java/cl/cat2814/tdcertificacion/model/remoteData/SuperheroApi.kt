package cl.cat2814.tdcertificacion.model.remoteData

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApi {

    // Obtención de listado de Superhéroes desde la Api.
    @GET("superheroes/")
    suspend fun getSuperheroesFromApi(): Response<List<Superhero>>

    // Obtención de detalle de Superhéroes desde la Api.
    @GET("superheroes/{id}")
    suspend fun getSuperheroesDetailFromApi(@Path("id") id: Int): Response<SuperheroDetail>
}
