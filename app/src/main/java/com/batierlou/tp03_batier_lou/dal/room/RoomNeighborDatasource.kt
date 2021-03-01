package com.batierlou.tp03_batier_lou.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.batierlou.tp03_batier_lou.dal.NeighborDatasource
import com.batierlou.tp03_batier_lou.dal.room.daos.NeighborDao
import com.batierlou.tp03_batier_lou.dal.utils.toEntity
import com.batierlou.tp03_batier_lou.dal.utils.toNeighbor
import com.batierlou.tp03_batier_lou.models.Neighbor

class RoomNeighborDataSource(application: Application) : NeighborDatasource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighbors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighbors.addSource(dao.getNeighbors()) { entities ->
            _neighbors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighbors

    override fun deleteNeighbor(neighbor: Neighbor) {
        dao.delete(neighbor.toEntity())
    }

    override fun createNeighbor(neighbor: Neighbor) {
        dao.add(neighbor.toEntity())
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
