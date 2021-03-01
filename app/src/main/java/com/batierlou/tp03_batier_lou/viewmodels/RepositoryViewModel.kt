package com.batierlou.tp03_batier_lou.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.batierlou.tp03_batier_lou.di.DI
import com.batierlou.tp03_batier_lou.models.Neighbor
import com.batierlou.tp03_batier_lou.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbors()
}