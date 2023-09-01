package cl.cat2814.tdcertificacion.model.repository

import cl.cat2814.tdcertificacion.model.remoteData.Superhero
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class MapperTest {

    @Test
    fun toEntity() {

        // Given
        val superheroTest = Superhero(1, "barman", "melmac","http://www.ejemplo.com",
        "eat himself", 178)

        // When
        val result =superheroTest.toEntity()

        // Then
        assertThat(result.id).isEqualTo(1)
        assertThat(result.name).isEqualTo("barman")
        assertThat(result.birthPlace).isEqualTo("melmac")
        assertThat(result.imageLink).isEqualTo("http://www.ejemplo.com")
        assertThat(result.superPower).isEqualTo("eat himself")
        assertThat(result.creationYear).isEqualTo(178)
    }
}
