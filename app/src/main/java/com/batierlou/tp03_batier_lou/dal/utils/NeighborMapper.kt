package com.batierlou.tp03_batier_lou.dal.utils

import NeighborEntity
import com.batierlou.tp03_batier_lou.models.Neighbor

fun NeighborEntity.toNeighbor() = Neighbor(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
    mail = mail,
    tel = tel,
    aboutMe = aboutMe,
    favorite = favorite,
    siteUrl = siteUrl ?: ""
)

fun Neighbor.toEntity() = NeighborEntity(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
    mail = mail,
    tel = tel,
    aboutMe = aboutMe,
    favorite = favorite ?: false,
    siteUrl = siteUrl
)
