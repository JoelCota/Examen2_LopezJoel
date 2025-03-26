package lopez.joel.examen2_lopezjoel.model

import java.io.Serializable

data class Cancion(
    val nombre: String,
    val artista: String,
    val duracion: String,
    val album: String,
): Serializable
