package com.batierlou.tp03_batier_lou.di

import android.app.Application
import com.batierlou.tp03_batier_lou.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}
