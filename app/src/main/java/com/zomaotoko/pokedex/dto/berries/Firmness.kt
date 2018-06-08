package com.zomaotoko.pokedex.dto.berries

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.zomaotoko.pokedex.dto.APIResource
import com.zomaotoko.pokedex.dto.Name

data class Firmness(
        var id: Int = 0,
        var name: String? = "",
        var berries: ArrayList<APIResource>? = null,
        var names: ArrayList<Name>? = null
)