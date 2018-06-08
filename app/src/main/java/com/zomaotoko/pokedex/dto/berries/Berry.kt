package com.zomaotoko.pokedex.dto.berries

import com.google.gson.annotations.SerializedName
import com.zomaotoko.pokedex.dto.APIResource

data class Berry(
        var id: Int = 0,
        var name: String? = null,
        @SerializedName("growth_time") var growthTime: Int = 0,
        @SerializedName("max_harvest") var maxHarvest: Int = 0,
        @SerializedName("natural_gift_power") var naturalGiftPower: Int = 0,
        var size: Int = 0,
        var smoothness: Int = 0,
        @SerializedName("soil_dryness") var soilDryness: Int = 0,
        var firmness: APIResource? = null,
        var flavors: ArrayList<Flavor>? = null,
        var item: APIResource? = null,
        @SerializedName("natural_gift_type") var naturalGiftType: APIResource? = null
)