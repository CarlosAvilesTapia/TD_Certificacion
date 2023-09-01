package cl.cat2814.tdcertificacion.model.remoteData

import com.google.gson.annotations.SerializedName

data class Superhero(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val birthPlace: String,
    @SerializedName("imagenLink")val imageLink: String,
    @SerializedName("poder") val superPower: String,
    @SerializedName("Año_creacion") val creationYear: Int
)
