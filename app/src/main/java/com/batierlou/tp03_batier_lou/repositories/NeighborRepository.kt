package com.batierlou.tp03_batier_lou.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.batierlou.tp03_batier_lou.dal.NeighborDatasource
import com.batierlou.tp03_batier_lou.dal.room.RoomNeighborDataSource
import com.batierlou.tp03_batier_lou.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val dataSource: NeighborDatasource

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbors(): LiveData<List<Neighbor>> = dataSource.neighbors

    fun delete(neighbor: Neighbor) = dataSource.deleteNeighbor(neighbor)
    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbor(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
