package com.zomaotoko.pokedex.apirequest

import com.zomaotoko.pokedex.dto.berries.Berry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BerriesService {
    @GET("api/v2/berry/{id}/")
    fun getBerry(@Path("id") id: Int) : Call<Berry>
}