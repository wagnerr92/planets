package rp.consulting.planets.data

import rp.consulting.planets.ui.main.PlanetData

class PlanetRepository {
    fun getPlanetList() : List<PlanetData>{
        return listOf(
            PlanetData("Terra", "Descricao Terra"),
            PlanetData("Jupiter", "Descricao Jupiter"),
            PlanetData("Mercúrio", "Descricao Mercúrio")
        )
    }
}