package com.batierlou.tp03_batier_lou.data

import com.batierlou.tp03_batier_lou.data.datasource.InMemoryNeighborDatasource
import com.batierlou.tp03_batier_lou.data.datasource.NeighborDatasource
import com.batierlou.tp03_batier_lou.models.Neighbor

class NeighborRepository {
    val dataSource: NeighborDatasource = InMemoryNeighborDatasource()

    // On ne veut pas qu'on puisse instancier le repository de l'extérieur; pour cela on rend son constructeur private
    private constructor() {

    }

    // Quand le repository est instancié, on charge instance la datasource aussi
    init {

    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): List<Neighbor> = dataSource.neighbors

    companion object {
        private var instance: NeighborRepository? = null
        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}