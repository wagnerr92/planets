package rp.consulting.planets.data.api

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class PlanetEntity (
    val id: Int,
    val name: String,
    val description: String,
    val url: String,
)