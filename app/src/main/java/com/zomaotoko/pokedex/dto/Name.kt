package com.zomaotoko.pokedex.dto

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.intellij.lang.annotations.Language

@Entity(tableName = "names")
data class Name(
        @PrimaryKey var name: String = "",
        var language: APIResource? = null
)