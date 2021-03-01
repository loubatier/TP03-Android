package com.batierlou.tp03_batier_lou.dal.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "neighbors")
class NeighborEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    var avatarUrl: String,
    val mail: String,
    var tel: String,
    var aboutMe: String,
    var favorite: Boolean = false,
    var siteUrl: String? = null,
)
