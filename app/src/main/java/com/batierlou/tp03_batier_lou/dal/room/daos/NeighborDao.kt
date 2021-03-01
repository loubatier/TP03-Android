package com.batierlou.tp03_batier_lou.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.batierlou.tp03_batier_lou.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun add(neighbor: NeighborEntity)

    @Delete
    fun delete(neighbor: NeighborEntity)
}
