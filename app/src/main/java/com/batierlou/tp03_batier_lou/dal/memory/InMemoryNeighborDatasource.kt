package com.batierlou.tp03_batier_lou.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batierlou.tp03_batier_lou.dal.InMemory_NeighborS
import com.batierlou.tp03_batier_lou.dal.NeighborDatasource
import com.batierlou.tp03_batier_lou.models.Neighbor

class InMemoryNeighborDatasource : NeighborDatasource {

    private val _neighbors = MutableLiveData<List<Neighbor>>()

    init {
        _neighbors.postValue(InMemory_NeighborS)
    }

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighbors

    override fun deleteNeighbor(neighbor: Neighbor) {
        InMemory_NeighborS.remove(neighbor)
        _neighbors.postValue(InMemory_NeighborS)
    }

    override fun createNeighbor(neighbor: Neighbor) {
        InMemory_NeighborS.add(neighbor)
        _neighbors.postValue(InMemory_NeighborS)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
