package com.zomaotoko.pokedex.dto

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class APIResource(
        var url: String = "",
        var name: String? = null
)