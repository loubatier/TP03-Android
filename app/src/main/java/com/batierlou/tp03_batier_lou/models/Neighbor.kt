package com.batierlou.tp03_batier_lou.models

data class Neighbor(
    val id: Long,
    val name: String,
    val avatarUrl: String,
    val mail: String,
    val tel: String,
    val aboutMe: String,
    val favorite: Boolean? = false,
    val siteUrl: String
)
