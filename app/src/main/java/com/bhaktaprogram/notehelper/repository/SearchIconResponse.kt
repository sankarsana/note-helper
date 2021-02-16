package com.bhaktaprogram.notehelper.repository

import com.google.gson.annotations.SerializedName

data class SearchIconResponse(
    @SerializedName("icons") val icons: List<IconResponse>
)

data class IconResponse(
    @SerializedName("icon_id") val iconId: Long,
    @SerializedName("raster_sizes") val rasterSizes: List<IconSize>,
    @SerializedName("vector_sizes") val vectorSizes: List<IconSize>,
    @SerializedName("tags") val tags: List<String>
)

data class IconSize(
    @SerializedName("formats") val urls: List<IconUrl>,
    @SerializedName("size") val size: Int
)

data class IconUrl(
    @SerializedName("preview_url") val url: String
)
