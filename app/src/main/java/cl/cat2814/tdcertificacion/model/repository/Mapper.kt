package cl.cat2814.tdcertificacion.model.repository

import cl.cat2814.tdcertificacion.model.localData.SuperheroDetailEntity
import cl.cat2814.tdcertificacion.model.localData.SuperheroEntity
import cl.cat2814.tdcertificacion.model.remoteData.Superhero
import cl.cat2814.tdcertificacion.model.remoteData.SuperheroDetail

fun Superhero.toEntity(): SuperheroEntity = SuperheroEntity(
    this.id,
    this.name,
    this.birthPlace,
    this.imageLink,
    this.superPower,
    this.creationYear
)

fun SuperheroDetail.toDetailEntity(): SuperheroDetailEntity = SuperheroDetailEntity(
    this.id,
    this.name,
    this.birthPlace,
    this.imageLink,
    this.superPower,
    this.creationYear,
    this.color,
    this.translation
)
