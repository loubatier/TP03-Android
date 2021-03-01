package com.batierlou.tp03_batier_lou.dal

import androidx.lifecycle.LiveData
import com.batierlou.tp03_batier_lou.models.Neighbor

interface NeighborDatasource {
    /**
     * Get all my Neighbors
     * @return [List]
     */
    val neighbors: LiveData<List<Neighbor>>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbor(neighbor: Neighbor)

    /**
     * Create a neighbour
     * @param neighbor: Neighbor
     */
    fun createNeighbor(neighbor: Neighbor)

    /**
     * Update "Favorite status" of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateFavoriteStatus(neighbor: Neighbor)

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateNeighbor(neighbor: Neighbor)
}
