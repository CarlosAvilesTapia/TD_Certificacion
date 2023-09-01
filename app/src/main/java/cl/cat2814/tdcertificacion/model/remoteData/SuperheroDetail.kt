package cl.cat2814.tdcertificacion.model.remoteData

import com.google.gson.annotations.SerializedName

data class SuperheroDetail(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val birthPlace: String,
    @SerializedName("imagenLink")val imageLink: String,
    @SerializedName("poder") val superPower: String,
    @SerializedName("año_creacion") val creationYear: Int,
    val color: String,
    @SerializedName("traduccion") val translation: Boolean
)
